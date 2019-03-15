package com.co.ceiba.entrenamiento.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.builders.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.dominio.builders.VehiculoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IVehiculoDao;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

@Service
public class RegistroVehiculoServiceImpl implements IRegistroVehiculoService{
	
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	
	private static final String MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO = "El vehículo ya se encuentra registrado";
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;
	 
	@Autowired
	private IVehiculoDao vehiculoDao;
	
	@Autowired
	private Estacionamiento estacionamiento;

	@Override
	public RegistroParqueadero registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(!TipoVehiculoEnum.CARRO.getDescripcion().equals(vehiculo.getTipoVehiculo()) &&
				!TipoVehiculoEnum.MOTO.getDescripcion().equals(vehiculo.getTipoVehiculo())) 
			throw new ParqueaderoException(MSJ_TIPO_VEHICULO_NO_PERMITIDO);
		
		int cantidadVehiculos = registroParqueaderoDao.getCantidadVehiculosPorTipoVehiculo(EstadoRegistroEnum.ACTIVO.getDescripcion(), vehiculo.getTipoVehiculo());
		
		if(!estacionamiento.existeCapacidad(vehiculo.getTipoVehiculo(), cantidadVehiculos))
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_CUPO);
		
		RegistroParqueaderoEntity registroActualVehiculo = registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registroActualVehiculo != null)
			throw new ParqueaderoException(MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO);
		
		estacionamiento.validarIngresoPorPlaca(vehiculo.getPlaca());
		
		VehiculoEntity vehiculoSistema = vehiculoDao.getVehiculoPorPlaca(vehiculo.getPlaca());
		if (vehiculoSistema == null) {
			vehiculoSistema = vehiculoDao.save(VehiculoBuilder.convertirAEntity(vehiculo));
		}
		
		RegistroParqueaderoEntity registroGuardado = registroParqueaderoDao.save(new RegistroParqueaderoEntity(null,vehiculoSistema,new Date(),null,EstadoRegistroEnum.ACTIVO.getDescripcion(),0d));
		return RegistroParqueaderoBuilder.convertirADominio(registroGuardado);
	}

}
