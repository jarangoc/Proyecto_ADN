package com.co.ceiba.entrenamiento.dominio.builder;

import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;

public class BuilderVehiculo {
	
	private Long id;
	
	private String placa;
	
	private Integer cilindraje;
	
	private String tipoVehiculo;
	
	public BuilderVehiculo() {
		this.id = 10l;
		this.placa = "ABO";
		this.cilindraje = 1000;
		this.tipoVehiculo = TipoVehiculoEnum.CARRO.getDescripcion();
	}
	
	public static BuilderVehiculo  unVehiculo() {
		return new BuilderVehiculo();

	}
	
	public Vehiculo build() {
		return new Vehiculo(id,placa,cilindraje,tipoVehiculo);
	}

	public BuilderVehiculo conId(Long id) {
		this.id = id;
		return this;
	}

	public BuilderVehiculo conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public BuilderVehiculo conCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public BuilderVehiculo conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
		
	}
	
}
