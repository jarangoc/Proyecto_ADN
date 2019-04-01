package com.co.ceiba.entrenamiento.dominio.unitarias;

import static com.co.ceiba.entrenamiento.dominio.builder.BuilderRegistroParqueaderoEntity.unRegistroParqueadero;
import static com.co.ceiba.entrenamiento.dominio.builder.BuilderVehiculo.unVehiculo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.SalidaVehiculoService;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SalidaVehiculoServiceTest {

	@Mock
	private RegistroParqueaderoRepository registroParqueaderoDao;
	
	@Mock
	private Estacionamiento parqueadero; 
	
	@InjectMocks
	private SalidaVehiculoService manejadorSalida;
	
	private static final String MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO = "El vehículo no se encuentra parqueado actualmente";
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void retirarVehiculoNoExistente() {
		//Arrange
		Vehiculo vehiculoARetirar = unVehiculo().conPlaca("CQR15A").build();
		when(registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		
		//Act
		try {
			manejadorSalida.registrarSalidaVehiculo(vehiculoARetirar.getPlaca());
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO,e.getMessage());
		}
	}
	
	@Test
	public void retirarVehiculo() throws ParqueaderoException {
		//Arrange
		Double valorCobrado = 500d;
		Vehiculo vehiculoARetirar = unVehiculo().conPlaca("CQR15A").build();
		RegistroParqueaderoEntity registroParqueaderoPorActualizar = unRegistroParqueadero().conVehiculo(com.co.ceiba.entrenamiento.persistencia.builder.VehiculoBuilder.convertirAEntity(vehiculoARetirar)).buil();
		when(registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(Mockito.anyString(), Mockito.anyString())).thenReturn(registroParqueaderoPorActualizar);
		registroParqueaderoPorActualizar.setFechaSalida(new Date());
		registroParqueaderoPorActualizar.setEstadoRegistro(EstadoRegistroEnum.INACTIVO.getDescripcion());
		registroParqueaderoPorActualizar.setValorCobrado(valorCobrado);		
		when(registroParqueaderoDao.save(registroParqueaderoPorActualizar)).thenReturn(registroParqueaderoPorActualizar);
		when(parqueadero.calcularPrecioParqueadero(Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.anyInt())).thenReturn(valorCobrado);
		
		//Act
		RegistroParqueadero registroRetirado = manejadorSalida.registrarSalidaVehiculo(vehiculoARetirar.getPlaca());
		
		//Assert
		assertEquals(EstadoRegistroEnum.INACTIVO.getDescripcion(),registroRetirado.getEstadoRegistro());
		assertEquals(valorCobrado,registroRetirado.getValorCobrado());
		assertEquals(registroParqueaderoPorActualizar.getFechaIngreso(),registroRetirado.getFechaIngreso());
		assertEquals(vehiculoARetirar.getPlaca(),registroRetirado.getVehiculo().getPlaca());		
	}
	

}
