package com.co.ceiba.entrenamiento;

import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest {
	
	private DateUtilsTest() {}

	public static Date crearFecha(int year,int month, int day, int hour, int minute) {
		Calendar fecha = Calendar.getInstance();
        //quitar horas a la fecha 
		fecha.set(year, month, day, hour, minute);
        return fecha.getTime();
	}
}
