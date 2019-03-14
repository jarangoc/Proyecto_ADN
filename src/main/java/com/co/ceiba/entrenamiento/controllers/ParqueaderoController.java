package com.co.ceiba.entrenamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.entrenamiento.dominio.ILibretaParqueaderoService;
import com.co.ceiba.entrenamiento.dominio.IRegistroVehiculoService;
import com.co.ceiba.entrenamiento.dominio.ISalidaVehiculoService;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.utils.Response;

@RestController
public class ParqueaderoController {
	
	private static final String MSJ_INGRESO_EXITOSO = "Se ha registrado correctamente el vehículo";
	
	private static final String MSJ_RETIRO_EXITOSO = "Se ha retirado correctamente el vehículo";
	
	private static final String MSJ_CONSULTA_VEHICULOS_EXITOSO = "Se ha consultado correctamente los vehículos del parqueadero";
	
	@Autowired
	private IRegistroVehiculoService registroVehiculoService;
	
	@Autowired
	private ISalidaVehiculoService salidaVehiculoService;
	
	@Autowired
	private ILibretaParqueaderoService libretaParqueaderoService ;
	
	@PostMapping("/registrarIngreso")
	public Response registrarIngresoVehiculo(@RequestBody Vehiculo vehiculo) {
		try {
			RegistroParqueadero registroGenerado = registroVehiculoService.registrarIngresoVehiculo(vehiculo);
			return new Response(registroGenerado, MSJ_INGRESO_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@PostMapping("/registrarSalida")
	public Response registrarSalidaVehiculo(@RequestBody Vehiculo vehiculo) {
		try {
			RegistroParqueadero registroSalida = salidaVehiculoService.registrarSalidaVehiculo(vehiculo);
			return new Response(registroSalida, MSJ_RETIRO_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}
	
	@GetMapping("/consultarVehiculo")	
	public Response consultarVehiculos() {
		try {
			return new Response(libretaParqueaderoService.consultarVehiculosEnParqueadero(), MSJ_CONSULTA_VEHICULOS_EXITOSO, 201, true);
		} catch (Exception e) {
			return new Response(null, e.getMessage(), 400, false);
		}
	}

}
