package com.co.ceiba.entrenamiento.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.entrenamiento.persistencia.entidades.RegistroParqueadero;

public interface IRegistroParqueaderoDao extends CrudRepository<RegistroParqueadero, Long>{
	
	@Query("SELECT rp FROM RegistroParqueadero rp WHERE LOWER(rp.estadoRegistro) = LOWER(:estadoRegistro) order by rp.vehiculo.tipoVehiculo")
    public List<RegistroParqueadero> getListadoVehiculosEnParqueadero(@Param("estadoRegistro") String estadoRegistro);
	
	@Query("SELECT count(rp.id) FROM RegistroParqueadero rp WHERE LOWER(rp.estadoRegistro) = LOWER(:estadoRegistro) AND LOWER(rp.vehiculo.tipoVehiculo) = LOWER(:tipoVehiculo)")
    public Integer getCantidadVehiculosPorTipoVehiculo(@Param("estadoRegistro") String estadoRegistro, @Param("tipoVehiculo") String tipoVehiculo);
	
	@Query("SELECT rp FROM RegistroParqueadero rp WHERE LOWER(rp.vehiculo.placa) = LOWER(:placa)  AND LOWER(rp.estadoRegistro) = LOWER(:estadoRegistro)")
	public RegistroParqueadero getRegistroParqueaderoPorPlacaYEstado(@Param("placa") String placa, @Param("estadoRegistro") String estadoRegistro);
	
}
