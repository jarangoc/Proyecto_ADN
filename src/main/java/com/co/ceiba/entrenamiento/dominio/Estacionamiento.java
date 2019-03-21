package com.co.ceiba.entrenamiento.dominio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.utils.DateUtils;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

@Component
public class Estacionamiento {

	private static final Character DIGITO_PLACA_NO_PERMITIDA = 'A';
	private static final Integer CILINDRAJE_MINIMO = 1;
	private static final Integer CILINDRAJE_MAXIMO = 9999;

	private static final String MSJ_VEHICULO_NO_IDENTIFICADO = "No fue posible identificar el tipo de vehículo ";
	private static final String MSJ_PLACA_NO_VALIDA = "La placa ingresada no es válida";
	private static final String MSJ_DIA_NO_HABIL = "No esta autorizado a ingresar,porque no es un dia hábil";
	private static final String MSJ_CILINDRAJE_NO_VALIDO = "El cilindraje no es correcto";

	@Autowired
	private IReglaParqueaderoCarro reglasCarro;

	@Autowired
	private IReglaParqueaderoMoto reglasMoto;

	public boolean existeCapacidad(String tipoVehiculo, Integer vehiculosActuales) throws ParqueaderoException {
		if (TipoVehiculoEnum.MOTO.getDescripcion().equals(tipoVehiculo)) {
			return reglasMoto.existeCupo(vehiculosActuales);
		}

		if (TipoVehiculoEnum.CARRO.getDescripcion().equals(tipoVehiculo)) {
			return reglasCarro.existeCupo(vehiculosActuales);
		}

		throw new ParqueaderoException(new StringBuilder(MSJ_VEHICULO_NO_IDENTIFICADO).append(tipoVehiculo).toString());
	}

	public void validarIngresoPorPlaca(String placa) throws ParqueaderoException {
		if (placa == null || "".equals(placa))
			throw new ParqueaderoException(MSJ_PLACA_NO_VALIDA);

		int diaActual = getDiaActual();
		if (DIGITO_PLACA_NO_PERMITIDA == (Character.toUpperCase(placa.charAt(0)))
				&& (Calendar.MONDAY == diaActual || Calendar.SUNDAY == diaActual))
			throw new ParqueaderoException(MSJ_DIA_NO_HABIL);
	}
	
	public Integer getDiaActual() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	
	public void validarCilindrajeVehiculo(Integer cilindraje) throws ParqueaderoException {
		if (cilindraje == null || cilindraje < CILINDRAJE_MINIMO || cilindraje > CILINDRAJE_MAXIMO)
			throw new ParqueaderoException(MSJ_CILINDRAJE_NO_VALIDO);
	}

	public Double calcularPrecioParqueadero(Date fechaIngreso, Date fechaSalida, String tipoVehiculo,
			Integer cilindraje) throws ParqueaderoException {
		TiempoParqueadero tiempoParqueadero = CalculadoraVigilanteParqueadero.calcularTiempoEntreFechas(fechaIngreso,
				fechaSalida);
		if (TipoVehiculoEnum.MOTO.getDescripcion().equals(tipoVehiculo)) {
			return reglasMoto.calcularPrecio(tiempoParqueadero, cilindraje);
		}

		if (TipoVehiculoEnum.CARRO.getDescripcion().equals(tipoVehiculo)) {
			return reglasCarro.calcularPrecio(tiempoParqueadero);
		}
		
		throw new ParqueaderoException(new StringBuilder(MSJ_VEHICULO_NO_IDENTIFICADO).append(tipoVehiculo).toString());
	}

}
