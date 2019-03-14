package com.co.ceiba.entrenamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.entrenamiento.dominio.IRegistroParqueaderoService;
import com.co.ceiba.entrenamiento.dominio.builders.VehiculoBuilder;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueaderoDTO;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoDTO;
import com.co.ceiba.entrenamiento.utils.Response;

@RestController
public class ParqueaderoController {
	
	private static final String MSJ_INGRESO_EXITOSO = "Se ha registrado correctamente el vehículo";
	
	private static final String MSJ_RETIRO_EXITOSO = "Se ha retirado correctamente el vehículo";
	
	private static final String MSJ_CONSULTA_VEHICULOS_EXITOSO = "Se ha consultado correctamente los vehículos del parqueadero";
	
	@Autowired
	private IRegistroParqueaderoService registroParqueaderoService;
	
	@PostMapping("/registrarIngreso")
	public Response registrarIngresoVehiculo(@RequestBody VehiculoDTO vehiculoDto) {
		try {
			RegistroParqueaderoDTO registroGenerado = registroParqueaderoService.registrarIngresoVehiculo(VehiculoBuilder.convertirAEntity(vehiculoDto));
			return new Response(registroGenerado, MSJ_INGRESO_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@PostMapping("/registrarSalida")
	public Response registrarSalidaVehiculo(@RequestBody VehiculoDTO vehiculoDto) {
		try {
			RegistroParqueaderoDTO registroSalida = registroParqueaderoService.registrarSalidaVehiculo(VehiculoBuilder.convertirAEntity(vehiculoDto));
			return new Response(registroSalida, MSJ_RETIRO_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@GetMapping("/consultarVehiculo")	
	public Response consultarVehiculos() {
		try {
			return new Response(registroParqueaderoService.consultarVehiculosEnParqueadero(), MSJ_CONSULTA_VEHICULOS_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}

}
