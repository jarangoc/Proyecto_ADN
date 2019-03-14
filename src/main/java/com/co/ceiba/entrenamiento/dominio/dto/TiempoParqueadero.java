package com.co.ceiba.entrenamiento.dominio.dto;

public class TiempoParqueadero {
	
	private Long cantidadDias;
	
	private Long cantidadHoras;
	
	private Long cantidadMinutos;

	public TiempoParqueadero(Long cantidadDias, Long cantidadHoras, Long cantidadMinutos) {
		this.cantidadDias = cantidadDias;
		this.cantidadHoras = cantidadHoras;
		this.cantidadMinutos = cantidadMinutos;
	}

	public Long getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Long cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public Long getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(Long cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public Long getCantidadMinutos() {
		return cantidadMinutos;
	}

	public void setCantidadMinutos(Long cantidadMinutos) {
		this.cantidadMinutos = cantidadMinutos;
	}
	
}
