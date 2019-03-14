package com.co.ceiba.entrenamiento.persistencia.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Long>{
	
	@Query("SELECT v FROM Vehiculo v WHERE LOWER(v.placa) = LOWER(:placa) ")
	public Vehiculo getVehiculoPorPlaca(@Param("placa") String placa);
	

}
