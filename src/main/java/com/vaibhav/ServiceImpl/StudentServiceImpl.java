package com.vaibhav.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.dto.StudentDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;
import com.vaibhav.repository.StudentRepository;
import com.vaibhav.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public List<StudentDTO> getAllStudents() {
		Iterable<Student> students= studentRepository.findAll();
		List<StudentDTO> studentlist= new ArrayList<>();
		for(Student student:students)
		{
			StudentDTO studentDTO= new StudentDTO();
			studentDTO.setRollNo(student.getRollNo());
			studentDTO.setName(student.getName());
			studentDTO.setMobile(student.getMobile());
			studentlist.add(studentDTO);
		}
		return studentlist;
	}

	@Override
	public Student addStudent(StudentDTO studentDTO) throws StudentException {
		Student student= new Student();
		if(Objects.nonNull(studentDTO.getRollNo()))
			student.setRollNo(studentDTO.getRollNo());
		else
			throw new StudentException("Student Roll no. is empty");
		
		if(Objects.nonNull(studentDTO.getName()))
			student.setName(studentDTO.getName());
		else
			throw new StudentException("Student name is empty");
		
		if(Objects.nonNull(studentDTO.getMobile()))
			student.setMobile(studentDTO.getMobile());
		else
			throw new StudentException("Student Mobile no. is empty");
		
		return studentRepository.save(student);
	}

}
