package com.fjdantas.cscmcourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjdantas.cscmcourse.domain.Category;
import com.fjdantas.cscmcourse.repositories.CategoryRepository;

/*
 * class of operation to fetch caterogy  by code for service layer
 */

@Service
public class CategoryService {
	
	/*
	 * dependency for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private CategoryRepository repo;
	
	/* 
	 * operation to find a category by code returning an Optional container object or a lambda expression
	 */
	public Category fetch(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}