package com.vaibhav.service;

import com.vaibhav.dto.AddressRequestDTO;
import com.vaibhav.dto.AddressResponseDTO;


public interface AddressService {
	
	public AddressResponseDTO addAddress(AddressRequestDTO addressDTO);

	public AddressResponseDTO getAddress(long id);
	
	public boolean deleteStudent(String id);
	
}
