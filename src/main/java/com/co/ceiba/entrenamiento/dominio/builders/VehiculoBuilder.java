package com.co.ceiba.entrenamiento.dominio.builders;

import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public final class VehiculoBuilder {
	
	private VehiculoBuilder() {}
	
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculo) {
		
		Vehiculo vehiculoDto = null;
		
		if(vehiculo != null) {
			vehiculoDto = new Vehiculo(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getCilindraje(), vehiculo.getTipoVehiculo());
		}
		
		return vehiculoDto;
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculoDto) {
		
		VehiculoEntity vehiculo = null;
		
		if(vehiculoDto != null) {
			vehiculo = new VehiculoEntity(vehiculoDto.getId(),vehiculoDto.getPlaca(),vehiculoDto.getCilindraje(),vehiculoDto.getTipoVehiculo()); 
		}
		
		return vehiculo;
	}

}
