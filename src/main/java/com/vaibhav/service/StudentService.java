package com.vaibhav.service;

import java.util.List;

import com.vaibhav.dto.StudentDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;



public interface StudentService {
	
	public Student addStudent(StudentDTO studentDTO) throws StudentException;

	public List<StudentDTO> getAllStudents();
}
