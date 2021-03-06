package com.fjdantas.cscmcourse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjdantas.cscmcourse.domain.Purchase;
import com.fjdantas.cscmcourse.services.PurchaseService;

@RestController
@RequestMapping(value="/purchases")
public class PurchaseResource {
	
	/*
	 * dependency for call operation of service access in the service layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private PurchaseService service;
	
	/*
	 * assigning http verbs getting a id by url
	 * storing a HTTP response for a REST Service with ResponseEntity special type of Spring
	 * getting an id parameter by url mapped by the @PathVariable annotation
	 */
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {  
		//accessing service by id
		Purchase obj = service.find(id); 
		//successful return operation and containing an object as the body of the response
		return ResponseEntity.ok().body(obj);
		
	}
	
}
