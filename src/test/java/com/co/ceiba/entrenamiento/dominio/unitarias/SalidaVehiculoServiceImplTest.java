package com.co.ceiba.entrenamiento.dominio.unitarias;

import static com.co.ceiba.entrenamiento.dominio.builder.VehiculoBuilderTest.unVehiculo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.SalidaVehiculoServiceImpl;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SalidaVehiculoServiceImplTest {

	@Spy
	private IRegistroParqueaderoDao registroParqueaderoDao;
	
	@Spy
	private Estacionamiento parqueadero; 
	
	@InjectMocks
	private SalidaVehiculoServiceImpl manejadorSalida;
	
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
	

}
