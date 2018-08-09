package com.fjdantas.cscmcourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjdantas.cscmcourse.domain.Purchase;
import com.fjdantas.cscmcourse.repositories.PurchaseRepository;
import com.fjdantas.cscmcourse.services.exceptions.ObjectNotFoundException;

/*
 * class of operation to fetch purchase order  by code for service layer
 */

@Service
public class PurchaseService {
	
	/*
	 * dependency for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private PurchaseRepository repo; //
	
	/* operation to find a purchase order by code returning an optional object or a lambda expression 
	 * through a function without arguments that instantiates an exception
	 */
	public Purchase find(Integer id) {
		Optional<Purchase> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Purchase.class.getName()));
	}
	
}
	