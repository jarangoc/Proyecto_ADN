package com.co.ceiba.entrenamiento.dominio;

import com.co.ceiba.entrenamiento.persistencia.entidades.Vehiculo;

public interface IVehiculoService {
	
	Vehiculo consultarVehiculoPorPlaca(String placa);
}
