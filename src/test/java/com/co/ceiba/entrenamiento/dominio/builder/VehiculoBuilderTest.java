package com.co.ceiba.entrenamiento.dominio.builder;

import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;

public class VehiculoBuilderTest {
	
	private Long id;
	
	private String placa;
	
	private Integer cilindraje;
	
	private String tipoVehiculo;
	
	public VehiculoBuilderTest() {
		this.id = 10l;
		this.placa = "ABO";
		this.cilindraje = 1000;
		this.tipoVehiculo = TipoVehiculoEnum.CARRO.getDescripcion();
	}
	
	public static VehiculoBuilderTest  unVehiculo() {
		return new VehiculoBuilderTest();

	}
	
	public Vehiculo build() {
		return new Vehiculo(id,placa,cilindraje,tipoVehiculo);
	}

	public VehiculoBuilderTest conId(Long id) {
		this.id = id;
		return this;
	}

	public VehiculoBuilderTest conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoBuilderTest conCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoBuilderTest conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
		
	}
	
}
