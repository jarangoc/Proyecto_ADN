package com.co.ceiba.entrenamiento.dominio.unitarias;

import static com.co.ceiba.entrenamiento.dominio.builder.BuilderRegistroParqueaderoEntity.unRegistroParqueadero;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import com.co.ceiba.entrenamiento.dominio.LibretaParqueaderoService;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibretaParqueaderoServiceTest {
	
	
	
	@Spy
	private RegistroParqueaderoRepository registroParqueaderoDao =  Mockito.mock(RegistroParqueaderoRepository.class);
	
	@InjectMocks
	private LibretaParqueaderoService libreta;
	
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
	public void validarExistenciaMinimaDeVehiculoEnParqueaderoCuandoEsNull() throws ParqueaderoException {
		
		//Arrange
		when(registroParqueaderoDao.getListadoVehiculosEnParqueadero(Mockito.anyString())).thenReturn(null);
		
		//Act
		List<VehiculoParqueadoDTO> resultado = libreta.consultarVehiculosEnParqueadero();
		
		
		//Assert
		assertEquals(0, resultado.size());
	}
	
	@Test
	public void validarNingunVehiculoEnParqueadero() throws ParqueaderoException{
		//Arrange
		
		List<RegistroParqueaderoEntity> registrosActivos = new ArrayList<>();
		
		when(registroParqueaderoDao.getListadoVehiculosEnParqueadero(Mockito.anyString())).thenReturn(registrosActivos);
		
		//Act
		 List<VehiculoParqueadoDTO> vehiculosEnParqueadero = libreta.consultarVehiculosEnParqueadero();
		 
		 //Assert
		assertTrue(vehiculosEnParqueadero.isEmpty());

	}

}
