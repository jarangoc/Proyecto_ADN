package com.co.ceiba.entrenamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;

@Service
public class LibretaParqueaderoService {
	
	@Autowired
	private RegistroParqueaderoRepository registroParqueaderoRepository;
	
	public List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() throws ParqueaderoException {
		List<RegistroParqueaderoEntity> registrosActivos = registroParqueaderoRepository.getListadoVehiculosEnParqueadero(EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registrosActivos == null || registrosActivos.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<VehiculoParqueadoDTO> vehiculosParqueados = new ArrayList<>();
		for (RegistroParqueaderoEntity registroParqueadero : registrosActivos) {
			VehiculoParqueadoDTO vehiculoParqueado = new VehiculoParqueadoDTO(
					registroParqueadero.getVehiculo().getPlaca(),
					registroParqueadero.getVehiculo().getTipoVehiculo(),
					registroParqueadero.getFechaIngreso());
			vehiculosParqueados.add(vehiculoParqueado);
		}
		return vehiculosParqueados;
	}
}
