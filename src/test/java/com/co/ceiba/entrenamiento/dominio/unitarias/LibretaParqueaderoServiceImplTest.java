package com.co.ceiba.entrenamiento.dominio.unitarias;

import static com.co.ceiba.entrenamiento.dominio.builder.RegistroParqueaderoEntityBuilder.unRegistroParqueadero;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.co.ceiba.entrenamiento.dominio.LibretaParqueaderoServiceImpl;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibretaParqueaderoServiceImplTest {
	
	
	
	@Spy
	private IRegistroParqueaderoDao registroParqueaderoDao =  Mockito.mock(IRegistroParqueaderoDao.class);
	
	@InjectMocks
	private LibretaParqueaderoServiceImpl libreta;
	
	private static final String MSJ_PARQUEADERO_SIN_VEHICULOS = "El parqueadero no tiene vehículos en el momento";
	
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validarExistenciaMinimaDeVehiculoEnParqueadero() throws ParqueaderoException {
		//Arrange
		
		List<RegistroParqueaderoEntity> registrosActivos = new ArrayList<>();
		RegistroParqueaderoEntity registro = unRegistroParqueadero().buil();
		registrosActivos.add(registro);
		
		when(registroParqueaderoDao.getListadoVehiculosEnParqueadero(Mockito.anyString())).thenReturn(registrosActivos);
		
		//Act
		List<VehiculoParqueadoDTO> resultado = libreta.consultarVehiculosEnParqueadero();
		
		
		//Assert
		assertEquals(1, resultado.size());
	}
	
	@Test
	public void validarNingunVehiculoEnParqueadero(){
		//Arrange
		
		List<RegistroParqueaderoEntity> registrosActivos = new ArrayList<>();
		
		when(registroParqueaderoDao.getListadoVehiculosEnParqueadero(Mockito.anyString())).thenReturn(registrosActivos);
		
		//Act
		try {
			libreta.consultarVehiculosEnParqueadero();
			fail();
		} catch (ParqueaderoException e) {
			assertEquals(MSJ_PARQUEADERO_SIN_VEHICULOS,e.getMessage());	
		}
	}

}
