package com.co.ceiba.entrenamiento.persistencia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Long>{
	

}
