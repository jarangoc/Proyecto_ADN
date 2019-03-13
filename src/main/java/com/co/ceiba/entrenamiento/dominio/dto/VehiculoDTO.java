package com.co.ceiba.entrenamiento.dominio.dto;

public class VehiculoDTO {
	

	private Long id;
	
	private String placa;
	
	private Integer cilindraje;
	
	private String tipoVehiculo;
	
	
	public VehiculoDTO() {
		
	}
	
	public VehiculoDTO(Long id, String placa, Integer cilindraje, String tipoVehiculo) {
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
