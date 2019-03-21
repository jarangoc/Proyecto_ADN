package com.co.ceiba.entrenamiento.utils;

import java.util.Calendar;

public class DateUtils {
	
	public Integer getDiaActual() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

}
