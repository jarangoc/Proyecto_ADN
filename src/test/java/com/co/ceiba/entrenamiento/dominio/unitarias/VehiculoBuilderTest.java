package com.co.ceiba.entrenamiento.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.co.ceiba.entrenamiento.dominio.builder.BuilderVehiculo;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;
import com.co.ceiba.entrenamiento.persistencia.builder.VehiculoBuilder;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilderTest {

	@Test
	public void crearVehiculoEntity() {
		//Arrange
		Vehiculo vehiculo = BuilderVehiculo.unVehiculo().conId(1l).conTipoVehiculo(TipoVehiculoEnum.CARRO.getDescripcion()).conCilindraje(1300).conPlaca("POY123").build();
		
		//Act
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		
		//Assert
		assertEquals(vehiculo.getId(),vehiculoEntity.getId());
		assertEquals(vehiculo.getTipoVehiculo(),vehiculoEntity.getTipoVehiculo());
		assertEquals(vehiculo.getCilindraje(),vehiculoEntity.getCilindraje());
		assertEquals(vehiculo.getPlaca(),vehiculoEntity.getPlaca());
	}
	
	@Test
	public void crearVehiculoEntityConDominioNull() {
		//Arrange
		
		//Act
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(null);
		
		//Assert
		assertEquals(null,vehiculoEntity);
	}
	
	@Test
	public void crearVehiculo() {
		//Arrange
		VehiculoEntity vehiculoEntity = new VehiculoEntity(6l,"POP112",200,TipoVehiculoEnum.MOTO.getDescripcion());
		
		//Act
		Vehiculo vehiculo = VehiculoBuilder.convertirADominio(vehiculoEntity);
		
		//Assert
		assertEquals(vehiculoEntity.getId(),vehiculo.getId());
		assertEquals(vehiculoEntity.getTipoVehiculo(),vehiculo.getTipoVehiculo());
		assertEquals(vehiculoEntity.getCilindraje(),vehiculo.getCilindraje());
		assertEquals(vehiculoEntity.getPlaca(),vehiculo.getPlaca());
	}
	
	@Test
	public void crearVehiculoConEntityNull() {
		//Arrange
		
		//Act
		Vehiculo vehiculo = VehiculoBuilder.convertirADominio(null);
		
		//Assert
		assertEquals(null,vehiculo);
	}
}
