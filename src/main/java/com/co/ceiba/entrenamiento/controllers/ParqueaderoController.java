package com.co.ceiba.entrenamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.entrenamiento.dominio.LibretaParqueaderoService;
import com.co.ceiba.entrenamiento.dominio.RegistroVehiculoService;
import com.co.ceiba.entrenamiento.dominio.SalidaVehiculoService;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class ParqueaderoController {

	@Autowired
	private RegistroVehiculoService registroVehiculoService;

	@Autowired
	private SalidaVehiculoService salidaVehiculoService;

	@Autowired
	private LibretaParqueaderoService libretaParqueadero;

	@PostMapping("/registros")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistroParqueadero registrarIngresoVehiculo(@RequestBody Vehiculo vehiculo) throws ParqueaderoException {
		return registroVehiculoService.registrarIngresoVehiculo(vehiculo);
	}

	@PatchMapping("/registros/{placa}")
	public RegistroParqueadero registrarSalidaVehiculo(@PathVariable String placa) throws ParqueaderoException {
		return salidaVehiculoService.registrarSalidaVehiculo(placa);
	}

	@GetMapping("/vehiculos")
	public List<VehiculoParqueadoDTO> consultarVehiculos() throws ParqueaderoException {
		return libretaParqueadero.consultarVehiculosEnParqueadero();
	}

}
