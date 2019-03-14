package com.co.ceiba.entrenamiento.dominio;

import java.util.List;

import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

public interface ILibretaParqueaderoService {
	
	List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() throws ParqueaderoException;

}
