package com.dxctraining.wishlist.dto;

public class CustomerDto {

	private Integer customerId;
	private String name;

	public Integer getCustId() {
		return customerId;
	}

	public void setCustomerId(int custId) {
		this.customerId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
