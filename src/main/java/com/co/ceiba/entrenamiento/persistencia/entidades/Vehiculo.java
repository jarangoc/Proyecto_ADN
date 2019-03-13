package com.co.ceiba.entrenamiento.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Vehiculo {

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private Long id;
	
	@NotEmpty
	@Column
	private String placa;
	
	@NotNull
	private Integer cilindraje;
	
	@NotEmpty
	@Column
	private String tipoVehiculo;
	
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(Long id, String placa, Integer cilindraje, String tipoVehiculo) {
		super();
		this.id = id;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}


	//GETTTERS AND SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
}