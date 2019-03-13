package com.co.ceiba.entrenamiento.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.builders.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueaderoDTO;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exceptions.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidades.RegistroParqueadero;
import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;
import com.co.ceiba.entrenamiento.persistencia.repositorios.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.utils.DateUtils;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

@Service
public class RegistroParqueaderoServiceImpl implements IRegistroParqueaderoService{
	
	private static final String MSJ_PARQUEADERO_SIN_VEHICULOS = "El parqueadero no tiene vehículos en el momento";
	
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	
	private static final String MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO = "El vehículo no se encuentra parqueado actualmente";
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;

	@Override
	public RegistroParqueaderoDTO registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(!TipoVehiculoEnum.CARRO.getDescripcion().equals(vehiculo.getTipoVehiculo()) &&
				!TipoVehiculoEnum.MOTO.getDescripcion().equals(vehiculo.getTipoVehiculo())) 
			throw new ParqueaderoException(MSJ_TIPO_VEHICULO_NO_PERMITIDO);
		
		int cantidadVehiculos = registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(EstadoRegistroEnum.ACTIVO.getDescripcion(), vehiculo.getTipoVehiculo());
		
		if(cantidadVehiculos>= Parqueadero.getCapacidadMaximaPorTipoVehiculo(vehiculo.getTipoVehiculo()))
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_CUPO);
		
		Parqueadero.validarIngresoPorPlaca(vehiculo.getPlaca());
		
		RegistroParqueadero registroGuardado = registroParqueaderoDao.save(new RegistroParqueadero(null,vehiculo,new Date(),null,EstadoRegistroEnum.ACTIVO.getDescripcion(),0d));
		return RegistroParqueaderoBuilder.convertirADominio(registroGuardado);
	}

	@Override
	public void registrarSalidaVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		RegistroParqueadero registroParqueadero = registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registroParqueadero == null)
			throw new ParqueaderoException(MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO);
		DateUtils.calcularTiempoEntreFechas(registroParqueadero.getFechaIngreso(), new Date());
		registroParqueaderoDao.save(new RegistroParqueadero());
	}

	@Override
	public List<VehiculoParqueadoDTO> consultarVehiculosEnParqueadero() throws ParqueaderoException {
		List<RegistroParqueadero> registrosActivos = registroParqueaderoDao.getListadoVehiculosEnParqueadero(EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registrosActivos == null || registrosActivos.isEmpty()) {
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_VEHICULOS);
		}
		
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
