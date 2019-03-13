package com.co.ceiba.entrenamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.persistencia.entidades.RegistroParqueadero;
import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;
import com.co.ceiba.entrenamiento.persistencia.repositorios.IRegistroParqueaderoDao;

@Service
public class RegistroParqueaderoServiceImpl implements IRegistroParqueaderoService{
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;

	@Override
	public void registrarIngresoVehiculo(Vehiculo vehiculo) {
		registroParqueaderoDao.save(new RegistroParqueadero());
	}

	@Override
	public void registrarSalidaVehiculo(Vehiculo vehiculo) {
		registroParqueaderoDao.save(new RegistroParqueadero());
	}

	@Override
	public List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() {
		List<RegistroParqueadero> registrosActivos = registroParqueaderoDao.getListadoVehiculosEnParqueadero("");
		List<VehiculoParqueadoDTO> vehiculosParqueados = new ArrayList<>();
		for (RegistroParqueadero registroParqueadero : registrosActivos) {
			VehiculoParqueadoDTO vehiculoParqueado = new VehiculoParqueadoDTO(
					registroParqueadero.getVehiculo().getPlaca(),
					registroParqueadero.getVehiculo().getTipoVehiculo(),
					registroParqueadero.getFechaIngreso());
			vehiculosParqueados.add(vehiculoParqueado);
		}
		return vehiculosParqueados;
	}



}
