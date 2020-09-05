package com.dxctraining.wishlist.dto;

public class WishedItemDto {

	private String id;

	private Integer CustomerId;

	private String productId;

	private String customerName;

	private String productName;

	public WishedItemDto() {
	}

	public WishedItemDto(String id, Integer customerId, String productId) {
		this.id = id;
		this.CustomerId = customerId;
		this.productId = productId;
	}
	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Integer customerId) {
		CustomerId = customerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
