package com.co.ceiba.entrenamiento.dominio;

import java.util.Date;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;

public class CalculadoraVigilanteParqueadero {
	
	private static final Long MILISEGUNDOSXDIA  = 86400000L;
	private static final Long MILISEGUNDOSXHORA = 3600000L;
	private static final Long MILISEGUNDOSXMINUTO = 60000L;

	private CalculadoraVigilanteParqueadero() {}
	
	public static TiempoParqueadero calcularTiempoEntreFechas(Date fechaInicial, Date fechaFinal) {
		Long tiempoTotalEnMilisegundos = fechaFinal.getTime() -fechaInicial.getTime();
		Long cantidadDias = tiempoTotalEnMilisegundos/MILISEGUNDOSXDIA;
		Long cantidadHorasAdicionales = ((tiempoTotalEnMilisegundos -(cantidadDias * MILISEGUNDOSXDIA))/MILISEGUNDOSXHORA);
		Long cantidadMinutosAdicionales = ((tiempoTotalEnMilisegundos -(cantidadDias * MILISEGUNDOSXDIA)) - cantidadHorasAdicionales * MILISEGUNDOSXHORA)/MILISEGUNDOSXMINUTO;
		return new TiempoParqueadero(cantidadDias, cantidadHorasAdicionales, cantidadMinutosAdicionales);
	}
}
