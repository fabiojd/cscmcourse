package com.fjdantas.cscmcourse.domain.enums;

public enum StatusPayment {
	//configuration of enums for saving into database
	PENDING(1, "Pending"),
	SETTLED(2, "Settled"),
	CANCELED(3, "Canceled");
	
	//variables to store the configured values for the enums
	private int code;
	private String description;
	
	/*
	 * private constructor for type enumerated to receive the code and description as parameters
	 */
	private StatusPayment(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	/*
	 * operation to receive a code and return an object of type StatusPayment 
	 * instantiated and configuration or a exception into static method
	 */	
	public static StatusPayment toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for(StatusPayment x : StatusPayment.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Id" + code);
	}

}
