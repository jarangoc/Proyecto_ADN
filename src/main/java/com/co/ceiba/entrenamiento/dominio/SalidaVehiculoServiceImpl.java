package com.co.ceiba.entrenamiento.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.entrenamiento.dominio.builders.RegistroParqueaderoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.repositorio.IRegistroParqueaderoDao;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;

@Service
public class SalidaVehiculoServiceImpl implements ISalidaVehiculoService{
	
	private static final String MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO = "El vehículo no se encuentra parqueado actualmente";
	
	@Autowired
	private IRegistroParqueaderoDao registroParqueaderoDao;
	
	@Autowired
	private Estacionamiento parqueadero; 

	@Override
	public RegistroParqueadero registrarSalidaVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		RegistroParqueaderoEntity registroParqueadero = registroParqueaderoDao.getRegistroParqueaderoPorPlacaYEstado(vehiculo.getPlaca(), EstadoRegistroEnum.ACTIVO.getDescripcion());
		Date fechaSalida =new Date();
		if(registroParqueadero == null)
			throw new ParqueaderoException(MSJ_NO_EXISTE_VEHICULO_EN_PARQUEADERO);
		
		double precioParqueadero = parqueadero.calcularPrecioParqueadero(registroParqueadero.getFechaIngreso(), fechaSalida, vehiculo.getTipoVehiculo(), vehiculo.getCilindraje());
		registroParqueadero.completarDatosRetiro(fechaSalida, precioParqueadero, EstadoRegistroEnum.INACTIVO.getDescripcion());
		RegistroParqueaderoEntity registroActualizado = registroParqueaderoDao.save(registroParqueadero);
		return RegistroParqueaderoBuilder.convertirADominio(registroActualizado);
	}

}
