package com.vaibhav.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vaibhav.dto.StudentDTO;
import com.vaibhav.entity.Student;
import com.vaibhav.exception.StudentException;
import com.vaibhav.resopnse.ControllerResponse;
import com.vaibhav.service.StudentService;

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
	public ResponseEntity<?> addAirlineDetails(@RequestBody StudentDTO studentDTO) {
		ControllerResponse response = new ControllerResponse();

		try {
			Student student = studentService.addStudent(studentDTO);
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
	public ResponseEntity<?> getAllAirlinesDetails() {

		ControllerResponse response = new ControllerResponse();

		List<StudentDTO> studentList = studentService.getAllStudents();
		if(studentList.isEmpty()) {
			response.setMessage("There is no student in database");
		}
		response.setStatus(HttpStatus.OK);
		response.setObject(studentList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}	
}
