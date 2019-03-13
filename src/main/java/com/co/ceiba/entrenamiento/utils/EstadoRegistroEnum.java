package com.co.ceiba.entrenamiento.utils;

public enum EstadoRegistroEnum {
	
	ACTIVO("Activo"),INACTIVO("Inactivo");
	
	private String descripcion;
	
	private EstadoRegistroEnum (String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
