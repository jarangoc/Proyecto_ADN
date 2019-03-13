package com.co.ceiba.entrenamiento.utils;

public enum TipoVehiculoEnum {
	
	MOTO("Moto"),CARRO("Carro");
	
	private String descripcion;
	
	private TipoVehiculoEnum (String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
