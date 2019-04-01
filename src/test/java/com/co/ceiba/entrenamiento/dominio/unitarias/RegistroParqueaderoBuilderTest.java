package com.co.ceiba.entrenamiento.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.co.ceiba.entrenamiento.DateUtilsTest;
import com.co.ceiba.entrenamiento.dominio.builder.BuilderVehiculo;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;
import com.co.ceiba.entrenamiento.persistencia.builder.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.persistencia.builder.VehiculoBuilder;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;


public class RegistroParqueaderoBuilderTest {

	@Test
	public void crearVehiculoEntity() {
		//Arrange
		Vehiculo vehiculo = BuilderVehiculo.unVehiculo().conId(1l).conTipoVehiculo(TipoVehiculoEnum.CARRO.getDescripcion()).conCilindraje(1300).conPlaca("POY123").build();
		Date fechaIngreso = DateUtilsTest.crearFecha(2019, 04, 1, 8, 37);
		Date fechaSalida = DateUtilsTest.crearFecha(2019, 04, 1, 10, 21);
		Double valorCobrado = 2000d;  
		RegistroParqueadero registroParqueaderoAConvertir = new RegistroParqueadero(1l, vehiculo, fechaIngreso, fechaSalida, EstadoRegistroEnum.INACTIVO.getDescripcion(),valorCobrado);
		
		
		//Act
		RegistroParqueaderoEntity registroParqueaderoEntity = RegistroParqueaderoBuilder.convertirAEntity(registroParqueaderoAConvertir);
		
		//Assert
		assertEquals(registroParqueaderoAConvertir.getId(),registroParqueaderoEntity.getId());
		assertEquals(registroParqueaderoAConvertir.getVehiculo().getId(),registroParqueaderoEntity.getVehiculo().getId());
		assertEquals(registroParqueaderoAConvertir.getFechaIngreso().getTime(),registroParqueaderoEntity.getFechaIngreso().getTime());
		assertEquals(registroParqueaderoAConvertir.getFechaSalida().getTime(),registroParqueaderoEntity.getFechaSalida().getTime());
		assertEquals(registroParqueaderoAConvertir.getEstadoRegistro(),registroParqueaderoEntity.getEstadoRegistro());
		assertEquals(registroParqueaderoAConvertir.getValorCobrado(),registroParqueaderoEntity.getValorCobrado());
	}
	
	@Test
	public void crearVehiculoEntityConDominioNull() {
		//Arrange
		
		//Act
		RegistroParqueaderoEntity registroParqueaderoEntity = RegistroParqueaderoBuilder.convertirAEntity(null);
		
		//Assert
		assertEquals(null,registroParqueaderoEntity);
	}
	
	@Test
	public void crearVehiculo() {
		//Arrange
		Vehiculo vehiculo = BuilderVehiculo.unVehiculo().conId(2l).conTipoVehiculo(TipoVehiculoEnum.MOTO.getDescripcion()).conCilindraje(900).conPlaca("MOT456").build();
		Date fechaIngreso = DateUtilsTest.crearFecha(2019, 06, 20, 9, 30);
		Date fechaSalida = DateUtilsTest.crearFecha(2019, 06, 21, 10, 10);
		Double valorCobrado = 6000d;  
		RegistroParqueaderoEntity registroParqueaderoAConvertir = new RegistroParqueaderoEntity(1l, VehiculoBuilder.convertirAEntity(vehiculo), fechaIngreso, fechaSalida, EstadoRegistroEnum.INACTIVO.getDescripcion(),valorCobrado);
		
		
		//Act
		RegistroParqueadero registroParqueadero = RegistroParqueaderoBuilder.convertirADominio(registroParqueaderoAConvertir);
		
		//Assert
		assertEquals(registroParqueaderoAConvertir.getId(),registroParqueadero.getId());
		assertEquals(registroParqueaderoAConvertir.getVehiculo().getId(),registroParqueadero.getVehiculo().getId());
		assertEquals(registroParqueaderoAConvertir.getFechaIngreso().getTime(),registroParqueadero.getFechaIngreso().getTime());
		assertEquals(registroParqueaderoAConvertir.getFechaSalida().getTime(),registroParqueadero.getFechaSalida().getTime());
		assertEquals(registroParqueaderoAConvertir.getEstadoRegistro(),registroParqueadero.getEstadoRegistro());
		assertEquals(registroParqueaderoAConvertir.getValorCobrado(),registroParqueadero.getValorCobrado());
	}
	
	@Test
	public void crearVehiculoConEntityNull() {
		//Arrange
		
		//Act
		RegistroParqueadero registroParqueadero = RegistroParqueaderoBuilder.convertirADominio(null);
		
		//Assert
		assertEquals(null,registroParqueadero);
	}
	
}
