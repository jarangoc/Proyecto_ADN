package com.co.ceiba.entrenamiento.dto;

import java.util.Date;

import com.co.ceiba.entrenamiento.entities.Vehiculo;

public class RegistroParqueaderoDTO {
	
	private Long id;
	
	private Vehiculo vehiculo;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private String estadoRegistro;
	
	private Double valorCobrado;
	
	public RegistroParqueaderoDTO() {
		
	}

	public RegistroParqueaderoDTO(Long id, Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida,
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
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
