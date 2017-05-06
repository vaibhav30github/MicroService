package com.vaibhav.service;

import java.util.List;

import com.vaibhav.dto.StudentRequestDTO;
import com.vaibhav.dto.StudentResponseDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;



public interface StudentService {
	
	public StudentResponseDTO addStudent(StudentRequestDTO studentDTO) throws StudentException;

	public List<StudentResponseDTO> getAllStudents() throws StudentException;
	
	public StudentResponseDTO getStudent(String id) throws StudentException;
	
	public boolean deleteStudent(String id) throws StudentException;
	
	public StudentResponseDTO update(StudentRequestDTO studentDTO, String id) throws StudentException;
}
