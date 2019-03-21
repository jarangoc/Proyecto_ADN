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

	public void conId(Long id) {
		this.id = id;
	}

	public void conVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void conEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public void conValorCobrado(Double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	
}
