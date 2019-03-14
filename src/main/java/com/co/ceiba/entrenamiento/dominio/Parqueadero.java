package com.co.ceiba.entrenamiento.dominio;

import java.util.Calendar;
import java.util.Date;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueaderoDTO;
import com.co.ceiba.entrenamiento.dominio.exceptions.ParqueaderoException;
import com.co.ceiba.entrenamiento.utils.DateUtils;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

public class Parqueadero {
	
	private  Parqueadero() {
		
	}

	public static final Integer CAPACIDAD_MOTO = 10;
	public static final Integer CAPACIDAD_CARRO = 20;
	public static final Double 	PRECIO_HORA_MOTO = 500d;
	public static final Double 	PRECIO_HORA_CARRO = 1000d;
	public static final Double 	PRECIO_DIA_MOTO = 4000d;
	public static final Double 	PRECIO_DIA_CARRO = 8000d;
	public static final Integer CILINDRAJE_COBRO_ADICIONAL_MOTO = 500;
	public static final Double 	PRECIO_CILINDRAJE_ADICIONAL_MOTO = 2000d;
	
	private static final Character DIGITO_PLACA_NO_PERMITIDA = 'A';
	private static final Integer TOPE_PARA_COBRO_POR_DIA = 9;
	
	private static final String MSJ_VEHICULO_NO_IDENTIFICADO = "No fue posible identificar el tipo de vehículo ";
	private static final String MSJ_PLACA_NO_VALIDA = "La placa ingresada no es válida";
	private static final String MSJ_DIA_NO_HABIL = "No esta autorizado a ingresar,porque no es un dia hábil";
	
	private static Integer getCapacidadMaximaPorTipoVehiculo(String tipoVehiculo) throws ParqueaderoException {
		if (TipoVehiculoEnum.MOTO.getDescripcion().equals(tipoVehiculo)) {
			return CAPACIDAD_MOTO;
		}

		if (TipoVehiculoEnum.CARRO.getDescripcion().equals(tipoVehiculo)) {
			return CAPACIDAD_CARRO;
		}

		throw new ParqueaderoException(
				new StringBuilder(MSJ_VEHICULO_NO_IDENTIFICADO)
						.append(tipoVehiculo).toString());
	}
	
	public static boolean existeCapacidad(String tipoVehiculo, Integer vehiculosActuales) throws ParqueaderoException {
		return vehiculosActuales< getCapacidadMaximaPorTipoVehiculo(tipoVehiculo);
	}
	
	public static void validarIngresoPorPlaca(String placa) throws ParqueaderoException {
		if(placa == null || "".equals(placa))
			throw new ParqueaderoException(MSJ_PLACA_NO_VALIDA);
		
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		if(DIGITO_PLACA_NO_PERMITIDA == (Character.toUpperCase(placa.charAt(0)))
				&& (Calendar.MONDAY == diaActual || Calendar.SUNDAY == diaActual)) 
			throw new ParqueaderoException(MSJ_DIA_NO_HABIL);
	}
	
	public static Double calcularPrecioParqueadero(Date fechaIngreso, Date fechaSalida, String tipoVehiculo,Integer cilindraje) throws ParqueaderoException {
		TiempoParqueaderoDTO tiempoParqueadero = DateUtils.calcularTiempoEntreFechas(fechaIngreso, fechaSalida);
		double valorLiquidado = 0d;
		if(TipoVehiculoEnum.CARRO.getDescripcion().equals(tipoVehiculo)) {
			valorLiquidado += tiempoParqueadero.getCantidadDias()  * PRECIO_DIA_CARRO;
			if( tiempoParqueadero.getCantidadHoras() > TOPE_PARA_COBRO_POR_DIA) {
				valorLiquidado +=  PRECIO_DIA_CARRO;
			}else {
				valorLiquidado += tiempoParqueadero.getCantidadHoras() * PRECIO_HORA_CARRO;				
			}
		}else if (TipoVehiculoEnum.MOTO.getDescripcion().equals(tipoVehiculo)) {
			valorLiquidado += tiempoParqueadero.getCantidadDias()  * PRECIO_DIA_MOTO;
			if( tiempoParqueadero.getCantidadHoras() > TOPE_PARA_COBRO_POR_DIA) {
				valorLiquidado +=  PRECIO_DIA_MOTO;
			}else {
				valorLiquidado += tiempoParqueadero.getCantidadHoras() * PRECIO_HORA_MOTO;				
			}
			if(CILINDRAJE_COBRO_ADICIONAL_MOTO < cilindraje) {
				valorLiquidado +=  PRECIO_CILINDRAJE_ADICIONAL_MOTO;
			}
		}else {
			throw new ParqueaderoException(MSJ_VEHICULO_NO_IDENTIFICADO);
		}
		return valorLiquidado;
	}
		
}
