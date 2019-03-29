package com.co.ceiba.entrenamiento.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.co.ceiba.entrenamiento.dominio.exception.ParqueaderoException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String MSJ_ERROR_INTERNO_DEL_SERVIDOR = "Ocurrió un error interno en el servidor";
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ParqueaderoException.class)
	public ResponseEntity<String> manejo(ParqueaderoException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manejarErroresTecnicos(Exception e){
        logger.error(e.getMessage(),e);
		return new ResponseEntity<>(MSJ_ERROR_INTERNO_DEL_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
