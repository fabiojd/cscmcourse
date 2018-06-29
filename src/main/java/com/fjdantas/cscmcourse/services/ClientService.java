package com.fjdantas.cscmcourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjdantas.cscmcourse.domain.Client;
import com.fjdantas.cscmcourse.repositories.ClientRepository;
import com.fjdantas.cscmcourse.services.exceptions.ObjectNotFoundException;

/*
 * class of operation to fetch client  by code for service layer
 */

@Service
public class ClientService {
	
	/*
	 * dependency for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private ClientRepository repo; //
	
	/* operation to find a client by code returning an optional object or a lambda expression 
	 * through a function without arguments that instantiates an exception
	 */
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
}
	