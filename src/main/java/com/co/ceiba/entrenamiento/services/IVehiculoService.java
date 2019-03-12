package com.co.ceiba.entrenamiento.services;

import com.co.ceiba.entrenamiento.entities.Vehiculo;

public interface IVehiculoService {
	
	Vehiculo consultarVehiculoPorPlaca(String placa);
}
