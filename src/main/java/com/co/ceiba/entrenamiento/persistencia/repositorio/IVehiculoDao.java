package com.co.ceiba.entrenamiento.persistencia.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public interface IVehiculoDao extends CrudRepository<VehiculoEntity, Long>{
	
	@Query("SELECT v FROM Vehiculo v WHERE LOWER(v.placa) = LOWER(:placa) ")
	public VehiculoEntity getVehiculoPorPlaca(@Param("placa") String placa);
	

}
