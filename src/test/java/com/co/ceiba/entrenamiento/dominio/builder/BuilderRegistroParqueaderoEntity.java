package com.co.ceiba.entrenamiento.dominio.builder;

import java.util.Date;

import com.co.ceiba.entrenamiento.DateUtilsTest;
import com.co.ceiba.entrenamiento.enums.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.enums.TipoVehiculoEnum;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;

public class BuilderRegistroParqueaderoEntity {
	
	private Long id;
	
	private VehiculoEntity vehiculo;
	
	private Date fechaIngreso; 
	
	private Date fechaSalida;
	
	private String estadoRegistro;
	
	private Double valorCobrado;
	
	private BuilderRegistroParqueaderoEntity() {
		this.id = 100L;
		this.vehiculo  = new VehiculoEntity(10L, "OUL25D", 100, TipoVehiculoEnum.MOTO.getDescripcion());
		this.fechaIngreso = DateUtilsTest.crearFecha(2019, 3, 21, 13, 15);
		this.fechaSalida= DateUtilsTest.crearFecha(2019, 3, 21, 13, 45);
		this.estadoRegistro = EstadoRegistroEnum.ACTIVO.getDescripcion();
		this.valorCobrado = 500d;
				
	}
	
	public static BuilderRegistroParqueaderoEntity unRegistroParqueadero() {
		return new BuilderRegistroParqueaderoEntity();
	}
	
	public RegistroParqueaderoEntity buil() {
		return new RegistroParqueaderoEntity(id,vehiculo,fechaIngreso,fechaSalida,estadoRegistro,valorCobrado);
	}

	public BuilderRegistroParqueaderoEntity conId(Long id) {
		this.id = id;
		return this;
	}

	public BuilderRegistroParqueaderoEntity conVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public BuilderRegistroParqueaderoEntity conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public BuilderRegistroParqueaderoEntity conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public BuilderRegistroParqueaderoEntity conEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
		return this;
	}

	public BuilderRegistroParqueaderoEntity conValorCobrado(Double valorCobrado) {
		this.valorCobrado = valorCobrado;
		return this;
	}
	
}
