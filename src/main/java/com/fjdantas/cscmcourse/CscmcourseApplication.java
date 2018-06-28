package com.fjdantas.cscmcourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fjdantas.cscmcourse.domain.Category;
import com.fjdantas.cscmcourse.repositories.CategoryRepository;

/*
 * class implements interface CommandLineRunner for objects instantiation in the initialization of the application
 */
@SpringBootApplication
public class CscmcourseApplication implements CommandLineRunner{

	/*
	 * dependencies for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CscmcourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//instantiating of the objects of categories
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}
}
