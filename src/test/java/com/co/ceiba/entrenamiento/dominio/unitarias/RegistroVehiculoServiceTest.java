package com.co.ceiba.entrenamiento.dominio.unitarias;

import static com.co.ceiba.entrenamiento.dominio.builder.RegistroParqueaderoEntityBuilder.unRegistroParqueadero;
import static com.co.ceiba.entrenamiento.dominio.builder.VehiculoBuilderTest.unVehiculo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.RegistroVehiculoService;
import com.co.ceiba.entrenamiento.dominio.builders.VehiculoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;
import com.co.ceiba.entrenamiento.persistencia.repositorio.VehiculoRepository;

public class RegistroVehiculoServiceTest {
	
	@Spy
	private RegistroParqueaderoRepository registroParqueaderoDao = Mockito.mock(RegistroParqueaderoRepository.class);
	 
	@Spy
	private VehiculoRepository vehiculoDao =Mockito.mock(VehiculoRepository.class) ;
	
	@Spy
	private Estacionamiento estacionamiento = Mockito.mock(Estacionamiento.class);
	
	@InjectMocks
	private RegistroVehiculoService registrador;
	
	private static final String MSJ_ERROR_VEHICULO_VACIO = "No se puede realizar la operación, no se envió correctamente la información del vehículo";
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	private static final String MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO = "El vehículo ya se encuentra registrado";
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void registrarVehiculoNull() {
		//Arrange
		
		//Act
		try {
			registrador.registrarIngresoVehiculo(null);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_ERROR_VEHICULO_VACIO,e.getMessage());	
		}	
	}
	
	@Test
	public void registrarVehiculoConTipoVehiculoCamion() {
		//Arrange
			Vehiculo vehiculoARegistrar = unVehiculo()
			.conTipoVehiculo("Canmion")
			.conPlaca("CAMION1").build();
		//Act
		try {
			registrador.registrarIngresoVehiculo(vehiculoARegistrar);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_TIPO_VEHICULO_NO_PERMITIDO ,e.getMessage());	
		}	
	}
	
	@Test
	public void registrarVehiculoSinCupoParqueadero() throws ParqueaderoException {
		//Arrange
			Vehiculo vehiculoARegistrar = unVehiculo()
			.conPlaca("MPY12E")
			.conCilindraje(650)
			.conTipoVehiculo("Moto")
			.build();
			when(registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(Mockito.anyString(),Mockito.anyString())).thenReturn(11);
			when(estacionamiento.existeCapacidad(Mockito.anyString(), Mockito.anyInt())).thenReturn(false);
			
		//Act
		try {
			registrador.registrarIngresoVehiculo(vehiculoARegistrar);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_PARQUEADERO_SIN_CUPO ,e.getMessage());	
		}	
	}
	
	@Test
	public void registrarVehiculoYaParqueado() throws ParqueaderoException {
		//Arrange
			Vehiculo vehiculoARegistrar = unVehiculo()
			.conPlaca("TTPQ16")
			.conCilindraje(200)
			.conTipoVehiculo("Moto")
			.build();
			RegistroParqueaderoEntity registroExistente = unRegistroParqueadero().conVehiculo(VehiculoBuilder.convertirAEntity(vehiculoARegistrar)).buil();
			
			when(registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(Mockito.anyString(),Mockito.anyString())).thenReturn(11);
			when(estacionamiento.existeCapacidad(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
			when(registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(Mockito.anyString(), Mockito.anyString())).thenReturn(registroExistente);
			
		//Act
		try {
			registrador.registrarIngresoVehiculo(vehiculoARegistrar);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO ,e.getMessage());	
		}	
	}
	
	@Test
	public void registrarVehiculo() throws ParqueaderoException {
		//Arrange
			Vehiculo vehiculoARegistrar = unVehiculo()
			.conPlaca("TTPQ16")
			.conCilindraje(200)
			.conTipoVehiculo("Moto")
			.build();
			RegistroParqueaderoEntity registroAGenerar = unRegistroParqueadero().conVehiculo(VehiculoBuilder.convertirAEntity(vehiculoARegistrar)).buil();			
			when(registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(Mockito.anyString(),Mockito.anyString())).thenReturn(11);
			when(estacionamiento.existeCapacidad(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
			when(registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
			when(vehiculoDao.getVehiculoPorPlaca(Mockito.anyString())).thenReturn(VehiculoBuilder.convertirAEntity(vehiculoARegistrar));
			when(registroParqueaderoDao.save(Mockito.any())).thenReturn(registroAGenerar);
			
		//Act
			RegistroParqueadero registroGuardado = registrador.registrarIngresoVehiculo(vehiculoARegistrar);
		//Assert
			assertEquals(vehiculoARegistrar.getPlaca() , registroGuardado.getVehiculo().getPlaca());
			assertEquals(vehiculoARegistrar.getTipoVehiculo() , registroGuardado.getVehiculo().getTipoVehiculo());
			assertEquals(vehiculoARegistrar.getCilindraje() , registroGuardado.getVehiculo().getCilindraje());
	}
}
