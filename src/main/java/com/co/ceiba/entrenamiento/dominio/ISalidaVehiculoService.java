package com.co.ceiba.entrenamiento.dominio;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

public interface ISalidaVehiculoService {
	
	RegistroParqueadero registrarSalidaVehiculo(String placa) throws ParqueaderoException;
	
}
