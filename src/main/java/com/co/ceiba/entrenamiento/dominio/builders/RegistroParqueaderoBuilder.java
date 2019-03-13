package com.co.ceiba.entrenamiento.dominio.builders;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueaderoDTO;
import com.co.ceiba.entrenamiento.persistencia.entidades.RegistroParqueadero;

public class RegistroParqueaderoBuilder {
	
	private RegistroParqueaderoBuilder() {}
	
	
	public static RegistroParqueaderoDTO convertirADominio(RegistroParqueadero registroParqueadero) {

		RegistroParqueaderoDTO registroParqueaderoDto = null;

		if (registroParqueadero != null) {
			registroParqueaderoDto = new RegistroParqueaderoDTO(registroParqueadero.getId(),
					registroParqueadero.getVehiculo(), registroParqueadero.getFechaIngreso(),
					registroParqueadero.getFechaSalida(), registroParqueadero.getEstadoRegistro(),
					registroParqueadero.getValorCobrado());
		}

		return registroParqueaderoDto;
	}
	
	public static RegistroParqueadero convertirAEntity(RegistroParqueaderoDTO registroParqueaderoDto) {

		RegistroParqueadero registroParqueadero = null;

		if (registroParqueaderoDto != null) {
			registroParqueadero = new RegistroParqueadero(registroParqueaderoDto.getId(),
					registroParqueaderoDto.getVehiculo(), registroParqueaderoDto.getFechaIngreso(),
					registroParqueaderoDto.getFechaSalida(), registroParqueaderoDto.getEstadoRegistro(),
					registroParqueaderoDto.getValorCobrado());
		}

		return registroParqueadero;
	}

}
