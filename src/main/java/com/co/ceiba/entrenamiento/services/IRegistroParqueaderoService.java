package com.co.ceiba.entrenamiento.services;

import java.util.List;

import com.co.ceiba.entrenamiento.entities.Vehiculo;
import com.co.ceiba.entrenamiento.utils.VehiculoParqueadoDTO;

public interface IRegistroParqueaderoService {
	
	void registrarIngresoVehiculo(Vehiculo vehiculo);
	
	void registrarSalidaVehiculo(Vehiculo vehiculo);
	
	List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero();

}
