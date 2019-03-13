package com.co.ceiba.entrenamiento.dominio;

import java.util.List;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueaderoDTO;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exceptions.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;

public interface IRegistroParqueaderoService {
	
	RegistroParqueaderoDTO registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	
	void registrarSalidaVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	
	List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() throws ParqueaderoException;

}
