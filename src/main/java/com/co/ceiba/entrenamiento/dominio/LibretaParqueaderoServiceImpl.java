package com.co.ceiba.entrenamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;

@Service
public class LibretaParqueaderoServiceImpl implements ILibretaParqueaderoService{
	
	private static final String MSJ_PARQUEADERO_SIN_VEHICULOS = "El parqueadero no tiene vehículos en el momento";
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;
	
	@Override
	public List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() throws ParqueaderoException {
		List<RegistroParqueaderoEntity> registrosActivos = registroParqueaderoDao.getListadoVehiculosEnParqueadero(EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registrosActivos == null || registrosActivos.isEmpty()) {
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_VEHICULOS);
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
