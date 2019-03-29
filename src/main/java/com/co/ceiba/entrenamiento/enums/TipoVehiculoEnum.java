package com.co.ceiba.entrenamiento.enums;

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
