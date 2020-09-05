package com.dxctraining.customer.dto;

public class CustomerDto {
	private int id;
	private String name;

	public CustomerDto() {
	}

	public CustomerDto(Integer id, String name) {
		this.id = id;
		this.name = name;
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

}
