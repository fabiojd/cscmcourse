package com.fjdantas.cscmcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fjdantas.cscmcourse.domain.enums.TypeClient;

@Entity
public class Client implements Serializable{ //class conversion in byte sequence
	//generating class version
	private static final long serialVersionUID = 1L;
	
	/*
	 * attribute of class
	 * storaging a type Integer into type attribute and 
	 * exporting a type TypeClient by getType method
	 */
	@Id //auto id generation strategy definition with IDENTITY
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	private String name;
	private String email;
	private String ssnOrEin;
	private Integer type; 
	
	/*
	 * association between Address and Client when the objet Client can serialized your Addresses
	 * controller of the cyclic reference
	 */	
	@JsonManagedReference
	@OneToMany(mappedBy="client")
	private List<Address> address = new ArrayList<>();
	
	/*
	 * using a character set to represent the weak entity Phone instantiated by HashSet 
	 */
	@ElementCollection
	@CollectionTable(name="TELEPHONE")
	private Set<String> telephone = new HashSet<>();

	public Client() {

	}

	public Client(Integer id, String name, String email, String ssnOrEin, TypeClient type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.ssnOrEin = ssnOrEin;
		this.type = type.getCode();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSsnOrEin() {
		return ssnOrEin;
	}

	public void setCpfOrCnpj(String ssnOrEin) {
		this.ssnOrEin = ssnOrEin;
	}
	
	//passing an integer value to the TypeClient enum and getting a TypeClient value to return from the method
	public TypeClient getType() {
		return TypeClient.toEnum(type);
	}

	public void setType(TypeClient type) {
		this.type = type.getCode();
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Set<String> getTelephone() {
		return telephone;
	}

	public void setTelephone(Set<String> telephone) {
		this.telephone = telephone;
	}

	//method equals for comparing objects by content
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}