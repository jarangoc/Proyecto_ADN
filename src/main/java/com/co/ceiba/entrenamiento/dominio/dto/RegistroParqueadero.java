package com.co.ceiba.entrenamiento.dominio.dto;

import java.util.Date;

import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public class RegistroParqueadero {
	
	private Long id;
	
	private VehiculoEntity vehiculo;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private String estadoRegistro;
	
	private Double valorCobrado;
	
	public RegistroParqueadero() {
		
	}

	public RegistroParqueadero(Long id, VehiculoEntity vehiculo, Date fechaIngreso, Date fechaSalida,
			String estadoRegistro, Double valorCobrado) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estadoRegistro = estadoRegistro;
		this.valorCobrado = valorCobrado;
	}
	
	//GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Double getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(Double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
}
