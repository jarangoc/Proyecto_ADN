package com.co.ceiba.entrenamiento.dao.builder;

import com.co.ceiba.entrenamiento.dto.VehiculoDTO;
import com.co.ceiba.entrenamiento.entities.Vehiculo;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {}
	
	
	public static VehiculoDTO convertirADominio(Vehiculo vehiculo) {
		
		VehiculoDTO vehiculoDto = null;
		
		if(vehiculo != null) {
			vehiculoDto = new VehiculoDTO(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getCilindraje(), vehiculo.getTipoVehiculo());
		}
		
		return vehiculoDto;
	}
	
	public static Vehiculo convertirAEntity(VehiculoDTO vehiculoDto) {
		
		Vehiculo vehiculo = null;
		
		if(vehiculoDto != null) {
			vehiculo = new Vehiculo(vehiculoDto.getId(),vehiculoDto.getPlaca(),vehiculoDto.getCilindraje(),vehiculoDto.getTipoVehiculo()); 
		}
		
		return vehiculo;
	}

}
