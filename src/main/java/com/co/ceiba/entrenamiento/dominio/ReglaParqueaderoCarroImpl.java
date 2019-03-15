package com.co.ceiba.entrenamiento.dominio;

import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;

@Service
public class ReglaParqueaderoCarroImpl implements IReglaParqueaderoCarro {

	private static final Integer CAPACIDAD_MAXIMA_CARRO = 20;

	private static final Double PRECIO_HORA_CARRO = 1000d;

	private static final Double PRECIO_DIA_CARRO = 8000d;
	
	private static final Integer TOPE_PARA_COBRO_POR_DIA = 9;

	@Override
	public Boolean existeCupo(Integer vehiculosActuales) {
		return vehiculosActuales < CAPACIDAD_MAXIMA_CARRO;
	}

	@Override
	public Double calcularPrecio(TiempoParqueadero tiempoParqueadero) {
		Double valorLiquidado = tiempoParqueadero.getCantidadDias() * PRECIO_DIA_CARRO;
		if (tiempoParqueadero.getCantidadHoras() >= TOPE_PARA_COBRO_POR_DIA) {
			valorLiquidado += PRECIO_DIA_CARRO;
		} else {
			valorLiquidado += tiempoParqueadero.getCantidadHoras() * PRECIO_HORA_CARRO;
		}
		return valorLiquidado;
	}

}
