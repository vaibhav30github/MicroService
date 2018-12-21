package com.vaibhav.dto;


public class StudentRequestDTO {

	
	private String name;
	
	private Long mobile;
	
	private AddressRequestDTO address;


	public AddressRequestDTO getAddress() {
		return address;
	}

	public void setAddress(AddressRequestDTO address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
}
