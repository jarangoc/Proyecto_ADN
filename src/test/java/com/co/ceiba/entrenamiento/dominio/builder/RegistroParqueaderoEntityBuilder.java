package com.co.ceiba.entrenamiento.dominio.builder;

import java.util.Date;

import com.co.ceiba.entrenamiento.DateUtilsTest;
import com.co.ceiba.entrenamiento.persistencia.entidad.RegistroParqueaderoEntity;
import com.co.ceiba.entrenamiento.persistencia.entidad.VehiculoEntity;
import com.co.ceiba.entrenamiento.utils.EstadoRegistroEnum;
import com.co.ceiba.entrenamiento.utils.TipoVehiculoEnum;

public class RegistroParqueaderoEntityBuilder {
	
	private Long id;
	
	private VehiculoEntity vehiculo;
	
	private Date fechaIngreso; 
	
	private Date fechaSalida;
	
	private String estadoRegistro;
	
	private Double valorCobrado;
	
	private RegistroParqueaderoEntityBuilder() {
		this.id = 100L;
		this.vehiculo  = new VehiculoEntity(10L, "OUL25D", 100, TipoVehiculoEnum.MOTO.getDescripcion());
		this.fechaIngreso = DateUtilsTest.crearFecha(2019, 3, 21, 13, 15);
		this.fechaSalida= DateUtilsTest.crearFecha(2019, 3, 21, 13, 45);
		this.estadoRegistro = EstadoRegistroEnum.ACTIVO.getDescripcion();
		this.valorCobrado = 500d;
				
	}
	
	public static RegistroParqueaderoEntityBuilder unRegistroParqueadero() {
		return new RegistroParqueaderoEntityBuilder();
	}
	
	public RegistroParqueaderoEntity buil() {
		return new RegistroParqueaderoEntity(id,vehiculo,fechaIngreso,fechaSalida,estadoRegistro,valorCobrado);
	}

	public RegistroParqueaderoEntityBuilder conId(Long id) {
		this.id = id;
		return this;
	}

	public RegistroParqueaderoEntityBuilder conVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public RegistroParqueaderoEntityBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public RegistroParqueaderoEntityBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public RegistroParqueaderoEntityBuilder conEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
		return this;
	}

	public RegistroParqueaderoEntityBuilder conValorCobrado(Double valorCobrado) {
		this.valorCobrado = valorCobrado;
		return this;
	}
	
}
