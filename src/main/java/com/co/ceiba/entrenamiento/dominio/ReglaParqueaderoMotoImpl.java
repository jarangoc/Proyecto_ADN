package com.co.ceiba.entrenamiento.dominio;

import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;

@Service
public class ReglaParqueaderoMotoImpl implements  IReglaParqueaderoMoto {

	private static final Integer CAPACIDAD_MAXIMA_MOTO = 10;

	private static final Double PRECIO_HORA_MOTO = 500d;

	private static final Double PRECIO_DIA_MOTO = 4000d;
	
	private static final Integer TOPE_PARA_COBRO_POR_DIA = 9;
	
	private static final Integer CILINDRAJE_COBRO_ADICIONAL_MOTO = 500;
	
	private static final Double 	PRECIO_CILINDRAJE_ADICIONAL_MOTO = 2000d;

	@Override
	public Boolean existeCupo(Integer vehiculosActuales) {
		return vehiculosActuales < CAPACIDAD_MAXIMA_MOTO;
	}

	@Override
	public Double calcularPrecio(TiempoParqueadero tiempoParqueadero,Integer cilindraje) {
		Double valorLiquidado = tiempoParqueadero.getCantidadDias()  * PRECIO_DIA_MOTO;
		if( tiempoParqueadero.getCantidadHoras() >= TOPE_PARA_COBRO_POR_DIA) {
			valorLiquidado +=  PRECIO_DIA_MOTO;
		}else {
			valorLiquidado += tiempoParqueadero.getCantidadHoras() * PRECIO_HORA_MOTO;				
		}
		if(CILINDRAJE_COBRO_ADICIONAL_MOTO < cilindraje) {
			valorLiquidado +=  PRECIO_CILINDRAJE_ADICIONAL_MOTO;
		}
		return valorLiquidado;
	}


}
