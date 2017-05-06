package com.vaibhav.service;

import java.util.List;

import com.vaibhav.dto.StudentRequestDTO;
import com.vaibhav.dto.StudentResponseDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;



public interface StudentService {
	
	public StudentResponseDTO addStudent(StudentRequestDTO studentDTO) throws StudentException;

	public List<StudentResponseDTO> getAllStudents();
	
	public StudentResponseDTO getStudent(String id);
}
