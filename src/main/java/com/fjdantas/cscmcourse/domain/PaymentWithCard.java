package com.fjdantas.cscmcourse.domain;

import javax.persistence.Entity;

import com.fjdantas.cscmcourse.domain.enums.StatusPayment;

@Entity
public class PaymentWithCard extends Payment{
	//generating class version
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;	
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, StatusPayment status, PurchaseOrder PurchaseOrder, Integer numberOfInstallments) {
		super(id, status, PurchaseOrder);
		this.setNumberOfInstallments(numberOfInstallments);
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
}
