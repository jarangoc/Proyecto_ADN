package com.co.ceiba.entrenamiento.utils;

import java.util.Date;

public class VehiculoParqueadoDTO {
	
	private String placa;
	
	private String tipoVehiculo;
	
	private Date fechaIngreso;
	
	public VehiculoParqueadoDTO () {
		
	}

	public VehiculoParqueadoDTO(String placa, String tipoVehiculo, Date fechaIngreso) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}
