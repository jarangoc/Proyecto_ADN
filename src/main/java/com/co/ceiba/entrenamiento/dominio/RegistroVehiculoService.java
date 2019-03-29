package com.co.ceiba.entrenamiento.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.builders.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.dominio.builders.VehiculoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;
import com.co.ceiba.entrenamiento.persistencia.repositorio.VehiculoRepository;

@Service
public class RegistroVehiculoService {
	
	private static final String MSJ_TIPO_VEHICULO_NO_PERMITIDO = "El tipo de vehículo no es permitido en el parqueadero";
	
	private static final String MSJ_PARQUEADERO_SIN_CUPO = "El parqueadero no tiene cupo";
	
	private static final String MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO = "El vehículo ya se encuentra registrado";
	
	private static final String MSJ_ERROR_VEHICULO_VACIO = "No se puede realizar la operación, no se envió correctamente la información del vehículo";
	
	
	@Autowired
	private RegistroParqueaderoRepository registroParqueaderoRepository;
	 
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private Estacionamiento estacionamiento;

	public RegistroParqueadero registrarIngresoVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(vehiculo == null) {
			throw new ParqueaderoException(MSJ_ERROR_VEHICULO_VACIO);
		}

		if(!TipoVehiculoEnum.CARRO.getDescripcion().equals(vehiculo.getTipoVehiculo()) &&
				!TipoVehiculoEnum.MOTO.getDescripcion().equals(vehiculo.getTipoVehiculo())) 
			throw new ParqueaderoException(MSJ_TIPO_VEHICULO_NO_PERMITIDO);
		
		int cantidadVehiculos = registroParqueaderoRepository.getCantidadVehiculosPorTipoVehiculo(EstadoRegistroEnum.ACTIVO.getDescripcion(), vehiculo.getTipoVehiculo());
		
		if(!estacionamiento.existeCapacidad(vehiculo.getTipoVehiculo(), cantidadVehiculos))
			throw new ParqueaderoException(MSJ_PARQUEADERO_SIN_CUPO);
		
		RegistroParqueaderoEntity registroActualVehiculo = registroParqueaderoRepository.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		if(registroActualVehiculo != null)
			throw new ParqueaderoException(MSJ_VEHICULO_EXISTENTE_EN_PARQUEADERO);
		
		estacionamiento.validarIngresoPorPlaca(vehiculo.getPlaca());
		
		estacionamiento.validarCilindrajeVehiculo(vehiculo.getCilindraje());;
		
		
		VehiculoEntity vehiculoSistema = vehiculoRepository.getVehiculoPorPlaca(vehiculo.getPlaca());
		if (vehiculoSistema == null) {
			vehiculoSistema = vehiculoRepository.save(VehiculoBuilder.convertirAEntity(vehiculo));
		}else {
			vehiculoSistema.setTipoVehiculo(vehiculo.getTipoVehiculo());
			vehiculoSistema.setCilindraje(vehiculo.getCilindraje());
			vehiculoRepository.save(vehiculoSistema);
		} 
		
		RegistroParqueaderoEntity registroGuardado = registroParqueaderoRepository.save(new RegistroParqueaderoEntity(null,vehiculoSistema,new Date(),null,EstadoRegistroEnum.ACTIVO.getDescripcion(),0d));
		return RegistroParqueaderoBuilder.convertirADominio(registroGuardado);
	}

}
