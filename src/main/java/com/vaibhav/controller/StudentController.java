package com.vaibhav.controller;


import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vaibhav.dto.StudentRequestDTO;
import com.vaibhav.dto.StudentResponseDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;
import com.vaibhav.resopnse.ControllerResponse;
import com.vaibhav.service.StudentService;

import io.swagger.annotations.ApiParam;

@RestController
public class StudentController {
	
	private static final Logger logger= Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
/*
 * Add Student
 * 
*/
	@RequestMapping(value="/addStudentDetails",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addStudentDetails(@RequestBody StudentRequestDTO studentDTO) {
		ControllerResponse response = new ControllerResponse();

		try {
			StudentResponseDTO student = studentService.addStudent(studentDTO);
			response.setStatus(HttpStatus.CREATED);
			response.setObject(student);
		} catch (StudentException e) {
			logger.error(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage(e.getMessage());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * Get all Student
	 * 
	*/
	@RequestMapping(value="/getStudentsDetails",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStudentsDetails() {

		ControllerResponse response = new ControllerResponse();

		List<StudentResponseDTO> studentList = studentService.getAllStudents();
		if(studentList.isEmpty()) {
			response.setMessage("There is no student in database");
		}
		response.setStatus(HttpStatus.OK);
		response.setObject(studentList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}	
	
	/*
	 * Get a Student
	 * 
	*/
	@ApiParam("Get a student")
	@RequestMapping(value="/getStudent{id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudent(@RequestParam String sstudentId) {

		ControllerResponse response = new ControllerResponse();

		StudentResponseDTO student = studentService.getStudent(sstudentId);
		if(Objects.isNull(student)) {
			response.setMessage("There is no student in database");
		}
		response.setStatus(HttpStatus.OK);
		response.setObject(student);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}		
}
