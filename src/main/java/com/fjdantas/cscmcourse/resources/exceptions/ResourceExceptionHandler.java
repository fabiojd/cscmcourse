package com.fjdantas.cscmcourse.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fjdantas.cscmcourse.services.exceptions.ObjectNotFoundException;

/*
 * class to receive and controller the exception and send the appropriate http response whit @ExceptionHandler annotation
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	//method that handles exceptions of type ObjectNotFoundException
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		//instantiating the StandardError object passing in the exception the status value for object not found
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()); 
		//returning the status of the error object of type StandardError in the body of the message
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
