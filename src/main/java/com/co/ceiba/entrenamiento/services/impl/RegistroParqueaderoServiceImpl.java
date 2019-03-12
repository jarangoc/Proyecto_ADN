package com.co.ceiba.entrenamiento.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dao.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.entities.RegistroParqueadero;
import com.co.ceiba.entrenamiento.entities.Vehiculo;
import com.co.ceiba.entrenamiento.services.IRegistroParqueaderoService;
import com.co.ceiba.entrenamiento.utils.VehiculoParqueadoDTO;

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
			VehiculoParqueadoDTO vehiculoParqueado = new VehiculoParqueadoDTO();
			vehiculoParqueado.setPlaca(registroParqueadero.getVehiculo().getPlaca());
			vehiculoParqueado.setTipoVehiculo(registroParqueadero.getVehiculo().getTipoVehiculo());
			vehiculoParqueado.setFechaIngreso(registroParqueadero.getFechaIngreso());
			vehiculosParqueados.add(vehiculoParqueado);
		}
		return vehiculosParqueados;
	}



}
