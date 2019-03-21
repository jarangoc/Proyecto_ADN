package com.co.ceiba.entrenamiento.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.RegistroVehiculoServiceImpl;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

import static com.co.ceiba.entrenamiento.dominio.builder.VehiculoBuilderTest.unVehiculo;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IVehiculoDao;

public class RegistroVehiculoServiceImplTest {
	
	
	
	
	@Spy
	private IRegistroParqueaderoDao registroParqueaderoDao = Mockito.mock(IRegistroParqueaderoDao.class);
	 
	@Spy
	private IVehiculoDao vehiculoDao =Mockito.mock(IVehiculoDao.class) ;
	
	@Spy
	private Estacionamiento estacionamiento = Mockito.mock(Estacionamiento.class);
	
	@InjectMocks
	private RegistroVehiculoServiceImpl registrador;
	
	private static final String MSJ_ERROR_VEHICULO_VACIO = "No se puede realizar la operación, no se envió correctamente la información del vehículo";
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	
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

}
