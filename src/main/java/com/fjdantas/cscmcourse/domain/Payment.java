package com.fjdantas.cscmcourse.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fjdantas.cscmcourse.domain.enums.StatusPayment;

/*
 * jpa entity class
 * mapping the inheritance with the @inheritance annotation 
 * and defining the strategy to generate individual tables in the database with InheritanceType.JOINED
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Payment implements Serializable{ //class conversion in byte sequence
	//generating class version
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer status;
	
	/*
	 * mapping the PurchaseOrder attribute to the order_id field 
	 * and defining the same PurchaseOrder id with the @MapsId annotation
	 */
	@OneToOne
	@JoinColumn(name="purchase_order_id")
	@MapsId
	private PurchaseOrder PurchaseOrder;
	
	public Payment() {
		
	}

	public Payment(Integer id, StatusPayment status, PurchaseOrder PurchaseOrder) {
		super();
		this.id = id;
		this.status = status.getCode();
		this.PurchaseOrder = PurchaseOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusPayment getStatus() {
		return StatusPayment.toEnum(status);
	}

	public void setStatus(StatusPayment status) {
		this.status = status.getCode();
	}

	public PurchaseOrder getOrder() {
		return PurchaseOrder;
	}

	public void setOrder(PurchaseOrder PurchaseOrder) {
		this.PurchaseOrder = PurchaseOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
