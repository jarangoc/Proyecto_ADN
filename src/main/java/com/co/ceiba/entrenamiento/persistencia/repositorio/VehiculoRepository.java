package com.co.ceiba.entrenamiento.persistencia.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public interface VehiculoRepository extends CrudRepository<VehiculoEntity, Long>{
	
	@Query("SELECT v FROM VehiculoEntity v WHERE LOWER(v.placa) = LOWER(:placa) ")
	public VehiculoEntity getVehiculoPorPlaca(@Param("placa") String placa);
	

}
