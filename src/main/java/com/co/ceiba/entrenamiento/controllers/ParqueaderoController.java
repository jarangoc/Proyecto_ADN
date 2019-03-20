package com.co.ceiba.entrenamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.entrenamiento.dominio.ILibretaParqueaderoService;
import com.co.ceiba.entrenamiento.dominio.IRegistroVehiculoService;
import com.co.ceiba.entrenamiento.dominio.ISalidaVehiculoService;
import com.co.ceiba.entrenamiento.dominio.dto.RegistroParqueadero;
import com.co.ceiba.entrenamiento.dominio.dto.Vehiculo;
import com.co.ceiba.entrenamiento.dominio.dto.VehiculoParqueadoDTO;
import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class ParqueaderoController {

	@Autowired
	private IRegistroVehiculoService registroVehiculoService;

	@Autowired
	private ISalidaVehiculoService salidaVehiculoService;

	@Autowired
	private ILibretaParqueaderoService libretaParqueaderoService;

	@PostMapping("/registrarIngreso")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistroParqueadero registrarIngresoVehiculo(@RequestBody Vehiculo vehiculo) throws ParqueaderoException {
		return registroVehiculoService.registrarIngresoVehiculo(vehiculo);

	}

	@PutMapping("/registrarSalida/{placa}")
	public RegistroParqueadero registrarSalidaVehiculo(@PathVariable String placa) throws ParqueaderoException {
		return salidaVehiculoService.registrarSalidaVehiculo(placa);
	}

	@GetMapping("/consultarVehiculo")
	public List<VehiculoParqueadoDTO> consultarVehiculos() throws ParqueaderoException {
		return libretaParqueaderoService.consultarVehiculosEnParqueadero();
	}

}
