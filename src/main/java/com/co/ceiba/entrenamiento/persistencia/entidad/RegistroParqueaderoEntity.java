package com.co.ceiba.entrenamiento.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class RegistroParqueaderoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne
	private VehiculoEntity vehiculo;
	
	@Temporal (TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date fechaIngreso;
	
	@Temporal (TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date fechaSalida;
	
	@NotEmpty
	@Column
	private String estadoRegistro;
	
	@Column
	private Double valorCobrado;
	
	
	public RegistroParqueaderoEntity() {
		
	}

	public RegistroParqueaderoEntity(Long id, VehiculoEntity vehiculo, Date fechaIngreso, Date fechaSalida,
			String estadoRegistro, Double valorCobrado) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estadoRegistro = estadoRegistro;
		this.valorCobrado = valorCobrado;
	}
	
	public void completarDatosRetiro(Date fechaSalida, Double valorCobrado, String estadoRegistro) {
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
