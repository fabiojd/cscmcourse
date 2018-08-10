package com.fjdantas.cscmcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity //jpa entity class
public class Category implements Serializable{ //class conversion in byte sequence
	//generating class version
	private static final long serialVersionUID = 1L;
	
	//attributes of the class
	@Id //auto id generation strategy definition with IDENTITY
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	private String name;
	
	/*
	 * avoiding cyclic reference with @JsonIgnore annotation in the mapped attribute
	 * creating the many-to-many relationship between the product and category tables _
	 * using the mapping done in the categories attribute into Product class
	 */
	
	@ManyToMany(mappedBy="categories")
	private List<Product> products = new ArrayList<>();
	
	//constructors of the class
	public Category() {
		
	}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	//method hashcode for generate number code for each object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	//method equals for comparing objects by content
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
	