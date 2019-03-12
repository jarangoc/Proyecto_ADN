package com.co.ceiba.entrenamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.entrenamiento.dao.builder.VehiculoBuilder;
import com.co.ceiba.entrenamiento.dto.VehiculoDTO;
import com.co.ceiba.entrenamiento.services.IRegistroParqueaderoService;
import com.co.ceiba.entrenamiento.utils.Response;

@RestController
public class ParqueaderoController {
	
	@Autowired
	private IRegistroParqueaderoService registroParqueaderoService;
	
	@PostMapping("/registrarIngreso")
	public Response registrarIngresoVehiculo(VehiculoDTO vehiculoDto) {
		try {
			registroParqueaderoService.registrarIngresoVehiculo(VehiculoBuilder.convertirAEntity(vehiculoDto));
			return new Response(null, "Se ha registrado correctamente el vehículo", 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@PostMapping("/registrarSalida")
	public Response registrarSalidaVehiculo(VehiculoDTO vehiculoDto) {
		try {
			registroParqueaderoService.registrarSalidaVehiculo(VehiculoBuilder.convertirAEntity(vehiculoDto));
			return new Response(null, "Se ha retirado correctamente el vehículo", 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@GetMapping("/consultarVehiculo")	
	public Response consultarVehiculo(String placa) {
		try {
			return new Response(registroParqueaderoService.consultarVehiculosEnParqueadero(), "", 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}

}
