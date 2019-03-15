package com.co.ceiba.entrenamiento.dominio;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;

public interface IReglaParqueaderoMoto {
	
	Boolean existeCupo(Integer vehiculosActuales);
	
	Double calcularPrecio(TiempoParqueadero tiempoParqueadero, Integer cilindraje);
	
}
