package com.co.ceiba.entrenamiento.dominio;

import com.co.ceiba.entrenamiento.dominio.dto.TiempoParqueadero;

public interface IReglaParqueaderoCarro {
	
	Boolean existeCupo(Integer vehiculosActuales);
	
	Double calcularPrecio(TiempoParqueadero tiempoParqueadero);

}
