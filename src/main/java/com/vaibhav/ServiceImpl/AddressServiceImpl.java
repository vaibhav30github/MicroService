package com.vaibhav.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaibhav.dto.AddressRequestDTO;
import com.vaibhav.dto.AddressResponseDTO;
import com.vaibhav.entity.Address;
import com.vaibhav.repository.AddrressRepository;
import com.vaibhav.service.AddressService;

public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddrressRepository addrressRepository;

	@Override
	public AddressResponseDTO addAddress(AddressRequestDTO addressDTO) {
		Address address = new Address();
		
		address.setCity(addressDTO.getCity());
		address.setPin(addressDTO.getPin());
		addrressRepository.save(address);
		
		return null;
	}

	@Override
	public AddressResponseDTO getAddress(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteStudent(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
