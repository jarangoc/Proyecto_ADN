package com.co.ceiba.entrenamiento.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.entrenamiento.entities.RegistroParqueadero;

public interface IRegistroParqueaderoDao extends CrudRepository<RegistroParqueadero, Long>{
	
	@Query("SELECT rp FROM RegistroParqueadero rp WHERE LOWER(rp.estadoRegistro) = LOWER(:estadoRegistro) order by rp.vehiculo.tipoVehiculo")
    public List<RegistroParqueadero> getListadoVehiculosEnParqueadero(@Param("estadoRegistro") String estadoRegistro);
	
}
