package com.co.ceiba.entrenamiento.dominio;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

public interface IRegistroVehiculoService {
	
	RegistroParqueadero registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException;

}
