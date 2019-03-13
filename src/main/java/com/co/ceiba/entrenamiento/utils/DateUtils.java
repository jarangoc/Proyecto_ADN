package com.co.ceiba.entrenamiento.utils;

import java.util.Date;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueaderoDTO;

public class DateUtils {
	
	private static final Long MILISEGUNDOSXDIA  = 86400000L;
	private static final Long MILISEGUNDOSXHORA = 3600000L;
	private static final Long MILISEGUNDOSXMINUTO = 60000L;

	private DateUtils() {}
	
	public static TiempoParqueaderoDTO calcularTiempoEntreFechas(Date fechaInicial, Date fechaFinal) {
		Long tiempoTotalEnMilisegundos = fechaFinal.getTime() -fechaInicial.getTime();
		Long cantidadDias = tiempoTotalEnMilisegundos/MILISEGUNDOSXDIA;
		Long cantidadHorasAdicionales = ((tiempoTotalEnMilisegundos -(cantidadDias * MILISEGUNDOSXDIA))/MILISEGUNDOSXHORA);
		Long cantidadMinutosAdicionales = ((tiempoTotalEnMilisegundos -(cantidadDias * MILISEGUNDOSXDIA)) - cantidadHorasAdicionales * MILISEGUNDOSXHORA)/MILISEGUNDOSXMINUTO;
		return new TiempoParqueaderoDTO(cantidadDias, cantidadHorasAdicionales, cantidadMinutosAdicionales);
	}
}
