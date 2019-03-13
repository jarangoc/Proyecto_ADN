package com.co.ceiba.entrenamiento.dominio;

import java.util.List;

import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;

public interface IRegistroParqueaderoService {
	
	void registrarIngresoVehiculo(Vehiculo vehiculo);
	
	void registrarSalidaVehiculo(Vehiculo vehiculo);
	
	List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero();

}
