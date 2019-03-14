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
import com.co.ceiba.entrenamiento.persistencia.repositorios.IVehiculoDao;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

@Service
public class RegistroParqueaderoServiceImpl implements IRegistroParqueaderoService{
	
	private static final String MSJ_PARQUEADERO_SIN_VEHICULOS = "El parqueadero no tiene vehículos en el momento";
	
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	
	private static final String MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO = "El vehículo no se encuentra parqueado actualmente";
	
	private static final String MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO = "El vehículo ya se encuentra registrado";
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;
	 
	@Autowired
	private IVehiculoDao vehiculoDao;

	@Override
	public RegistroParqueaderoDTO registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(!TipoVehiculoEnum.CARRO.getDescripcion().equals(vehiculo.getTipoVehiculo()) &&
				!TipoVehiculoEnum.MOTO.getDescripcion().equals(vehiculo.getTipoVehiculo())) 
			throw new ParqueaderoException(MSJ_TIPO_VEHICULO_NO_PERMITIDO);
		
		int cantidadVehiculos = registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(EstadoRegistroEnum.ACTIVO.getDescripcion(), vehiculo.getTipoVehiculo());
		
		if(!Parqueadero.existeCapacidad(vehiculo.getTipoVehiculo(), cantidadVehiculos))
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_CUPO);
		
		RegistroParqueadero registroActualVehiculo = registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registroActualVehiculo != null)
			throw new ParqueaderoException(MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO);
		
		Parqueadero.validarIngresoPorPlaca(vehiculo.getPlaca());
		
		Vehiculo vehiculoSistema = vehiculoDao.getVehiculoPorPlaca(vehiculo.getPlaca());
		if (vehiculoSistema == null) {
			vehiculoSistema = vehiculoDao.save(vehiculo);
		}
		
		RegistroParqueadero registroGuardado = registroParqueaderoDao.save(new RegistroParqueadero(null,vehiculoSistema,new Date(),null,EstadoRegistroEnum.ACTIVO.getDescripcion(),0d));
		return RegistroParqueaderoBuilder.convertirADominio(registroGuardado);
	}

	@Override
	public RegistroParqueaderoDTO registrarSalidaVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		RegistroParqueadero registroParqueadero = registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		Date fechaSalida =new Date();
		if(registroParqueadero == null)
			throw new ParqueaderoException(MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO);
		
		double precioParqueadero = Parqueadero.calcularPrecioParqueadero(registroParqueadero.getFechaIngreso(), fechaSalida, vehiculo.getTipoVehiculo(), vehiculo.getCilindraje());
		registroParqueadero.completarDatosRetiro(fechaSalida, precioParqueadero, EstadoRegistroEnum.INACTIVO.getDescripcion());
		RegistroParqueadero registroActualizado = registroParqueaderoDao.save(registroParqueadero);
		return RegistroParqueaderoBuilder.convertirADominio(registroActualizado);
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
