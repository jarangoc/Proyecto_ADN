package com.co.ceiba.entrenamiento.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.co.ceiba.entrenamiento.DateUtilsTest;
import com.co.ceiba.entrenamiento.dominio.Estacionamiento;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParqueaderoTest {
	
	private static final String MSJ_VEHICULO_NO_IDENTIFICADO = "No fue posible identificar el tipo de vehículo Camion";
	
	private static final String MSJ_CILINDRAJE_NO_VALIDO = "El cilindraje no es correcto";
	
	private static final String TIPO_VEHICULO_CAMION = "Camion";
	
	private static final String MSJ_PLACA_NO_VALIDA = "La placa ingresada no es válida";
	
	private static final String MSJ_DIA_NO_HABIL = "No esta autorizado a ingresar,porque no es un dia hábil";
	
	@Autowired
	private Estacionamiento parqueadero;
	
	@Spy
	private Estacionamiento parqueaderoMock;
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void calcularPrecioCarro1Dia3Horas() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 3, 13, 14, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 3, 14, 17, 0);
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
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 3, 13, 11, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 3, 13, 20, 0);
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		Double precioEsperado = 8000d;
		int cilindraje = 1300;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void calcularPrecioCarro15Minutos() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 3, 29, 14, 15);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 3, 29, 14, 30);
		String tipoVehiculoCarro = TipoVehiculoEnum.CARRO.getDescripcion();
		Double precioEsperado = 1000d;
		int cilindraje = 1300;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	
	@Test
	public void calcularPrecioMoto59Minutos() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 4, 26, 9, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 4, 26, 9, 59);
		String tipoVehiculoCarro = TipoVehiculoEnum.MOTO.getDescripcion();
		Double precioEsperado = 500d;
		int cilindraje = 250;
		
		//Act
		Double precioCalculado = parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, tipoVehiculoCarro, cilindraje);
		
		//Assert
		assertEquals(precioEsperado, precioCalculado, 1);
	}
	

	@Test
	public void calcularPrecioMoto10HorasCilindraje650() throws ParqueaderoException {
		//Arrange
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 8, 7, 6, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 8, 7, 16, 0);
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
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 2, 14, 3, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 2, 14, 11, 0);
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
		Date fechaInicial = DateUtilsTest.crearFecha(2019, 8, 7, 6, 0);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 8, 7, 16, 0);
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
		Date fechaInicial = DateUtilsTest.crearFecha(2017, 6, 10, 6, 12);
		Date fechaFinal = DateUtilsTest.crearFecha(2019, 6, 15, 16, 33);
		
		//Act
		try {
			parqueadero.calcularPrecioParqueadero(fechaInicial, fechaFinal, TIPO_VEHICULO_CAMION, 500);
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_VEHICULO_NO_IDENTIFICADO,e.getMessage());
		}
	}
	
	@Test
	public void validarCilindrajeCuandoEsNull() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarCilindrajeVehiculo(null);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_CILINDRAJE_NO_VALIDO,e.getMessage());
		}		
	}
	
	
	
	@Test
	public void validarCilindrajeCuandoEsMenorQueUno() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarCilindrajeVehiculo(0);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_CILINDRAJE_NO_VALIDO,e.getMessage());
		}		
	}
	
	@Test
	public void validarCilindrajeCuandoValorEsCorrecto() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarCilindrajeVehiculo(200);
		} catch (ParqueaderoException e) {
			//Assert
			fail();
		}		
	}
	
	@Test
	public void validarCilindrajeCuandoEsMayorQue9999() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarCilindrajeVehiculo(10000);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_CILINDRAJE_NO_VALIDO,e.getMessage());
		}		
	}
	
	@Test
	public void validarIngresoVehiculoConPlacaNull() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarIngresoPorPlaca(null);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_PLACA_NO_VALIDA,e.getMessage());
		}
	}
	
	@Test
	public void validarIngresoVehiculoConPlacaVacia() {
		//Arrange
		
		//Act
		try {
			parqueadero.validarIngresoPorPlaca("");
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_PLACA_NO_VALIDA,e.getMessage());
		}
	}
	
	
	
	@Test
	public void validarIngresoVehiculoConPlacaAXB125DiaLunes() {
		//Arrange
		String placa = "AXB125";
		when(parqueaderoMock.getDiaActual()).thenReturn(Calendar.MONDAY);
		
		
		//Act
		try {
			parqueaderoMock.validarIngresoPorPlaca(placa);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_DIA_NO_HABIL,e.getMessage());
		}
	}
	
	
	@Test
	public void validarIngresoVehiculoConPlacaAQP656DiaViernes() {
		//Arrange
		String placa = "AQP656";
		when(parqueaderoMock.getDiaActual()).thenReturn(Calendar.FRIDAY);
		
		//Act
		try {
			parqueaderoMock.validarIngresoPorPlaca(placa);
		} catch (ParqueaderoException e) {
			//Assert
			fail();
		}
	}
	
	
	@Test
	public void validarIngresoVehiculoConPlacaACQ993DiaDomingo() {
		//Arrange
		String placa = "ACQ993";
		when(parqueaderoMock.getDiaActual()).thenReturn(Calendar.SUNDAY);
		
		//Act
		try {
			parqueaderoMock.validarIngresoPorPlaca(placa);
			fail();
		} catch (ParqueaderoException e) {
			//Assert
			assertEquals(MSJ_DIA_NO_HABIL,e.getMessage());
		}
	}
	
	@Test
	public void validarIngresoVehiculoConPlacaXTH392DiaDomingo() {
		//Arrange
		String placa = "XTH392";
		when(parqueaderoMock.getDiaActual()).thenReturn(Calendar.SUNDAY);
		
		//Act
		try {
			parqueaderoMock.validarIngresoPorPlaca(placa);
		} catch (ParqueaderoException e) {
			//Assert
			fail();
		}
	}

}
