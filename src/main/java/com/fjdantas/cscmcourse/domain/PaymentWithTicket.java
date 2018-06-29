package com.fjdantas.cscmcourse.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fjdantas.cscmcourse.domain.enums.StatusPayment;

@Entity
public class PaymentWithTicket extends Payment{
	//generating class version
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paymentDate;
	
	public PaymentWithTicket() {
		
	}

	public PaymentWithTicket(Integer id, StatusPayment status, PurchaseOrder PurchaseOrder, Date dueDate, Date paymentDate) {
		super(id, status, PurchaseOrder);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
