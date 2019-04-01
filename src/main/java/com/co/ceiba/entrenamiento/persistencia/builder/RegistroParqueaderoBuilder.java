package com.co.ceiba.entrenamiento.persistencia.builder;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;

public final class RegistroParqueaderoBuilder {
	
	private RegistroParqueaderoBuilder() {}
	
	
	public static RegistroParqueadero convertirADominio(RegistroParqueaderoEntity registroParqueadero) {

		RegistroParqueadero registroParqueaderoDto = null;

		if (registroParqueadero != null) {
			registroParqueaderoDto = new RegistroParqueadero(registroParqueadero.getId(),
					VehiculoBuilder.convertirADominio(registroParqueadero.getVehiculo()), registroParqueadero.getFechaIngreso(),
					registroParqueadero.getFechaSalida(), registroParqueadero.getEstadoRegistro(),
					registroParqueadero.getValorCobrado());
		}

		return registroParqueaderoDto;
	}
	
	public static RegistroParqueaderoEntity convertirAEntity(RegistroParqueadero registroParqueaderoDto) {

		RegistroParqueaderoEntity registroParqueadero = null;

		if (registroParqueaderoDto != null) {
			registroParqueadero = new RegistroParqueaderoEntity(registroParqueaderoDto.getId(),
					VehiculoBuilder.convertirAEntity(registroParqueaderoDto.getVehiculo()), registroParqueaderoDto.getFechaIngreso(),
					registroParqueaderoDto.getFechaSalida(), registroParqueaderoDto.getEstadoRegistro(),
					registroParqueaderoDto.getValorCobrado());
		}

		return registroParqueadero;
	}

}
