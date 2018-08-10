package com.fjdantas.cscmcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //jpa entity class
public class Product implements Serializable{ //class conversion in byte sequence
	//generating class version
	private static final long serialVersionUID = 1L;
	
	//attributes of the class
	@Id //auto id generation strategy definition with IDENTITY
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	private String name;
	private Double price;
	
	/*
	 * avoiding cyclic reference with @JsonIgnore annotation in the mapped attribute
	 * creating the category list mapping and informing which _
	 * table will create the relationship between the product and category tables _
	 * and passing the foreign keys of the related tables
	 */
	
	@JsonIgnore
	@ManyToMany
	//PRODUCT_CATEGORY table that will make the many-to-many relationship between the product and category tables 
	@JoinTable(name="PRODUCT_CATEGORY", 
		joinColumns = @JoinColumn(name="product_id"), 
		inverseJoinColumns = @JoinColumn(name="category_id") //foreign keys to many-to-many relationship
	)	
	private List<Category> categories = new ArrayList<>();
	
	/*
	 * @JsonIgnore annotation will avoid serializing the collection
	 * controlling the duplicity of items in the same order
	 * mapping by id that is the auxiliary object that has the reference of the Product
	 */
	@JsonIgnore
	@OneToMany(mappedBy="id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
		
	}
	
	//constructor using fields of the class except categories
	public Product(Integer id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	/*
	 * adding into list object all the purchases related to each item in the OrderItem list
	 */
	@JsonIgnore
	public List<Purchase> getPurchases() {
		List<Purchase> list = new ArrayList<>();
		for(OrderItem x : items) {
			list.add(x.getPurchase());
		}
		return list;		
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	//methods hashcode for generate number code for each object
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
