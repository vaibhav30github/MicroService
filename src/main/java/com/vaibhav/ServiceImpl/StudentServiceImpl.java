package com.vaibhav.ServiceImpl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.dto.AddressRequestDTO;
import com.vaibhav.dto.AddressResponseDTO;
import com.vaibhav.dto.StudentRequestDTO;
import com.vaibhav.dto.StudentResponseDTO;
import com.vaibhav.entity.Address;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;
import com.vaibhav.repository.StudentRepository;
import com.vaibhav.service.AddressService;
import com.vaibhav.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public List<StudentResponseDTO> getAllStudents() {
		Iterable<Student> students= studentRepository.findAll();
		List<StudentResponseDTO> studentlist= new ArrayList<>();
		for(Student student:students)
		{
			StudentResponseDTO studentResponseDTO= new StudentResponseDTO();
			studentResponseDTO.setId(student.getId());
			studentResponseDTO.setMobile(student.getMobile());
			studentResponseDTO.setName(student.getName());
			studentlist.add(studentResponseDTO);
		}
		return studentlist;
	}

	@Override
	public StudentResponseDTO addStudent(StudentRequestDTO studentDTO) throws StudentException {
		Student student= new Student();
		Address address = new Address();
		StudentResponseDTO studentResponseDTO =null;
		
		if(Objects.nonNull(studentDTO.getName()))
			student.setName(studentDTO.getName());
		else
			throw new StudentException("Student name is empty");
		
		if(Objects.nonNull(studentDTO.getMobile()))
			student.setMobile(studentDTO.getMobile());
		else
			throw new StudentException("Student Mobile no. is empty");
		
		if(Objects.nonNull(studentDTO.getAddress()))
		{
			address.setCity(studentDTO.getAddress().getCity());
			address.setPin(studentDTO.getAddress().getPin());
			student.setAddress(address);
		} else 
			throw new StudentException("Student Address is empty");
		student.setId(getId());
		
		//address service
		//AddressService addressService = new AddressServiceImpl();
		
		// persist a student		
		studentRepository.save(student);
		//addressService.addAddress(studentDTO.getAddress());
		
		studentResponseDTO =new StudentResponseDTO();
		
		studentResponseDTO.setId(student.getId());
		studentResponseDTO.setMobile(student.getMobile());
		studentResponseDTO.setName(student.getName());
		AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
		addressResponseDTO.setAddId(student.getAddress().getAddId());
		addressResponseDTO.setCity(student.getAddress().getCity());
		addressResponseDTO.setPin(student.getAddress().getPin());
		studentResponseDTO.setAddress(addressResponseDTO);
		return studentResponseDTO;
	}
	
	
	@Override
	public StudentResponseDTO getStudent(String id) {
		
		StudentResponseDTO studentResponseDTO=null;
		
		Student student = studentRepository.findOne(id);
		
		if(Objects.nonNull(student)){
		studentResponseDTO =new StudentResponseDTO();
		
		studentResponseDTO.setId(student.getId());
		studentResponseDTO.setMobile(student.getMobile());
		studentResponseDTO.setName(student.getName());
		}
		
		return studentResponseDTO;
		
	}
	
	
	@Override
	public boolean deleteStudent(String id) throws StudentException {
		boolean result= false;
		
		
		if(Objects.nonNull(studentRepository.findOne(id))){
			studentRepository.delete(id);
			result= true;
		}else {
			throw new StudentException("Student id not found");
		}
		
		return result;
	}

	@Override
	public StudentResponseDTO update(StudentRequestDTO studentDTO, String id) throws StudentException {
		Student student = studentRepository.findOne(id);
		if(Objects.nonNull(student)){
			student.setName(studentDTO.getName());
			student.setMobile(studentDTO.getMobile());
			studentRepository.save(student);
		}else{
			throw new StudentException("Student id not found");
		}
		StudentResponseDTO studentResponseDTO= new StudentResponseDTO();
		studentResponseDTO.setId(student.getId());
		studentResponseDTO.setMobile(student.getMobile());
		studentResponseDTO.setName(student.getName());
		
		return studentResponseDTO;
	}

	/*	
	Get Unique Id
	
	*/
	private static String getId()
	{return (new BigInteger(199, new SecureRandom()).toString(36));}

}
