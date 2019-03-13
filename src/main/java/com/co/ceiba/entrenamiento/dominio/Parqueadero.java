package com.co.ceiba.entrenamiento.dominio;

import java.util.Calendar;

import com.co.ceiba.entrenamiento.dominio.exceptions.ParqueaderoException;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

public class Parqueadero {
	
	private  Parqueadero() {
		
	}

	public static final Integer CAPACIDAD_MOTO = 10;
	public static final Integer CAPACIDAD_CARRO = 20;
	public static final Double 	PRECIO_HORA_MOTO = 500d;
	public static final Double 	PRECIO_HORA_CARRO = 1000d;
	public static final Double 	PRECIO_DIA_MOTO = 8000d;
	public static final Double 	PRECIO_DIA_CARRO = 4000d;
	public static final Integer CILINDRAJE_COBRO_ADICIONAL_MOTO = 500;
	public static final Double 	PRECIO_CILINDRAJE_ADICIONAL_MOTO = 2000d;
	
	private static final Character DIGITO_PLACA_NO_PERMITIDA = 'A';
	
	private static final String MSJ_VEHICULO_NO_IDENTIFICADO = "No fue posible determinar la capacitdad para el tipo de vehículo ";
	private static final String MSJ_PLACA_NO_VALIDA = "La placa ingresada no es válida";
	private static final String MSJ_DIA_NO_HABIL = "No esta autorizado a ingresar,porque no es un dia hábil";
	
	public static Integer getCapacidadMaximaPorTipoVehiculo(String tipoVehiculo) throws ParqueaderoException {
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
	
	public static void validarIngresoPorPlaca(String placa) throws ParqueaderoException {
		if(placa == null || "".equals(""))
			throw new ParqueaderoException(MSJ_PLACA_NO_VALIDA);
		
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		if(DIGITO_PLACA_NO_PERMITIDA == (Character.toUpperCase(placa.charAt(0)))
				&& (Calendar.MONDAY == diaActual || Calendar.SUNDAY == diaActual)) 
			throw new ParqueaderoException(MSJ_DIA_NO_HABIL);
	}
		
}
