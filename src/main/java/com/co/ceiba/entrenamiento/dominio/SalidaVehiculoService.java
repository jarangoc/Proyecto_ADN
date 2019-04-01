package com.co.ceiba.entrenamiento.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.persistencia.builder.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.RegistroParqueaderoRepository;

@Service
public class SalidaVehiculoService{
	
	private static final String MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO = "El vehículo no se encuentra parqueado actualmente";
	
	@Autowired
	private RegistroParqueaderoRepository registroParqueaderoRepository;
	
	@Autowired
	private Estacionamiento parqueadero; 

	public RegistroParqueadero registrarSalidaVehiculo(String placa) throws ParqueaderoException {
		RegistroParqueaderoEntity registroParqueadero = registroParqueaderoRepository.getRegistroParqueaderoPorPlacaYEstado(placa, EstadoRegistroEnum.ACTIVO.getDescripcion());
		Date fechaSalida =new Date();
		if(registroParqueadero == null)
			throw new ParqueaderoException(MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO);
		
		double precioParqueadero = parqueadero.calcularPrecioParqueadero(registroParqueadero.getFechaIngreso(), fechaSalida, registroParqueadero.getVehiculo().getTipoVehiculo(), registroParqueadero.getVehiculo().getCilindraje());
		registroParqueadero.completarDatosRetiro(fechaSalida, precioParqueadero, EstadoRegistroEnum.INACTIVO.getDescripcion());
		RegistroParqueaderoEntity registroActualizado = registroParqueaderoRepository.save(registroParqueadero);
		return RegistroParqueaderoBuilder.convertirADominio(registroActualizado);
	}

}
