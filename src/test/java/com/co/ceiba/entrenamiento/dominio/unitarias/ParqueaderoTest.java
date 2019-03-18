package com.co.ceiba.entrenamiento.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParqueaderoTest {
	
	private static final String MSJ_VEHICULO_NO_IDENTIFICADO = "No fue posible identificar el tipo de vehículo Camion";
	
	private static final String TIPO_VEHICULO_CAMION = "Camion";
	
	@Autowired
	private Estacionamiento parqueadero;
	

	@Test
	public void calcularPrecioCarro1Dia3Horas() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2019, 3, 13, 14, 0);
		Date fechaFinal = crearFecha(2019, 3, 14, 17, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		Double precioEsperado = 11000d;
		int cilindraje = 1000;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void calcularPrecioCarro9Horas() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2019, 3, 13, 11, 0);
		Date fechaFinal = crearFecha(2019, 3, 13, 20, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		Double precioEsperado = 8000d;
		int cilindraje = 1300;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	

	@Test
	public void calcularPrecioMoto10HorasCilindraje650() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2019, 8, 7, 6, 0);
		Date fechaFinal = crearFecha(2019, 8, 7, 16, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		Double precioEsperado = 6000d;
		int cilindraje = 650;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void calcularPrecioMoto8HorasCilindraje125() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2019, 2, 14, 3, 0);
		Date fechaFinal = crearFecha(2019, 2, 14, 11, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		Double precioEsperado = 4000d;
		int cilindraje = 125;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void calcularPrecioMoto10HorasCilindraje150() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2019, 8, 7, 6, 0);
		Date fechaFinal = crearFecha(2019, 8, 7, 16, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		Double precioEsperado = 4000d;
		int cilindraje = 150;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void existeCupo19carros () throws ParqueaderoException {
		//Arrange
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		int cantidadCarrosActuales = 19;
		//Act
		boolean existeCupo = parqueadero.existeCapacidad(tipoVehiculoCarro, cantidadCarrosActuales);
		
		//Assert
		assertTrue(existeCupo);
	}
	
	@Test
	public void noExisteCupo20carros () throws ParqueaderoException {
		//Arrange
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		int cantidadCarrosActuales = 20;
		//Act
		boolean existeCupo =  parqueadero.existeCapacidad(tipoVehiculoCarro, cantidadCarrosActuales);
		
		//Assert
		assertFalse(existeCupo);
	}
	
	@Test
	public void existeCupo9Motos () throws ParqueaderoException {
		//Arrange
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		int cantidadCarrosActuales = 9;
		//Act
		boolean existeCupo =  parqueadero.existeCapacidad(tipoVehiculoCarro, cantidadCarrosActuales);
		
		//Assert
		assertTrue(existeCupo);
	}
	
	@Test
	public void noExisteCupo10Motos () throws ParqueaderoException {
		//Arrange
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		int cantidadCarrosActuales = 10;
		//Act
		boolean existeCupo = parqueadero.existeCapacidad(tipoVehiculoCarro, cantidadCarrosActuales);
		
		//Assert
		assertFalse(existeCupo);
	}
	
	
	@Test
	public void tipoVehiculoNoIdentificado () throws ParqueaderoException {
		//Arrange
		String tipoVehiculoCamion = TIPO_VEHICULO_CAMION;
		int cantidadCarrosActuales = 0;
		//Act
		try {
			parqueadero.existeCapacidad(tipoVehiculoCamion, cantidadCarrosActuales);
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_VEHICULO_NO_IDENTIFICADO,e.getMessage());
		}
	}
	
	@Test
	public void tipoVehiculoNoIdentificadoCalculandoPrecio () throws ParqueaderoException {
		//Arrange
		Date fechaInicial = crearFecha(2017, 6, 10, 6, 12);
		Date fechaFinal = crearFecha(2019, 6, 15, 16, 33);
		
		//Act
		try {
			parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, TIPO_VEHICULO_CAMION, 500);
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_VEHICULO_NO_IDENTIFICADO,e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void validarIngresoPlaca() throws ParqueaderoException {
		//Arrange
		
		//Act
		
		parqueadero.validarIngresoPorPlaca("APX58E");
		
		//Assert
		
		
	}
	
	private Date crearFecha(int year,int month, int day, int hour, int minute) {
		Calendar fecha = Calendar.getInstance();
        //quitar horas a la fecha 
		fecha.set(year, month, day, hour, minute);
        return fecha.getTime();
	}
}
