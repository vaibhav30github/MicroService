package com.vaibhav.controller;


import java.util.List;
import java.util.Objects;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.vaibhav.dto.StudentRequestDTO;
import com.vaibhav.dto.StudentResponseDTO;
import com.vaibhav.exception.StudentException;
import com.vaibhav.service.StudentService;
import com.vaibhav.view.ExcelView;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudentController {
	
	private static final Logger logger= Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
/*
 * Add Student
 * 
*/
	@ApiOperation(value="Add Student")
	@RequestMapping(value="/addStudentDetails",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addStudentDetails(@RequestBody StudentRequestDTO studentDTO) throws StudentException {
	
		StudentResponseDTO student = studentService.addStudent(studentDTO);
		/*try {
			
			response.setStatus(HttpStatus.CREATED);
			response.setObject(student);
		} catch (StudentException e) {
			logger.error(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage(e.getMessage());
		}
*/
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	/*
	 * Get all Student
	 * 
	*/
	@ApiOperation(value="Fetch all Students")
	@RequestMapping(value="/getStudentsDetails",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStudentsDetails() throws StudentException {

		

		List<StudentResponseDTO> studentList = studentService.getAllStudents();
		if(studentList.isEmpty()) {
			throw new StudentException("No record Found");
		}

		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}	
	
	/*
	 * Get a Student
	 * 
	*/
	@ApiOperation(value="Fetch a Student")
	@RequestMapping(value="/getStudent/{id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudent(@PathVariable String id) throws StudentException {


		StudentResponseDTO student = studentService.getStudent(id);
		if(Objects.isNull(student)) {
			throw new StudentException("There is no student in database");
		}
	
		return new ResponseEntity<>(student, HttpStatus.OK);
	}		
/*
* delete Studet
*/
	@ApiOperation(value="Delete Student")
	@RequestMapping(value="/deleteStudent/{id}",method = RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteStudent(@PathVariable String id) throws StudentException {


		boolean result = studentService.deleteStudent(id);

		String response=null;
		if(!result) {
			throw new StudentException("There is no student in database");
		}else{
		 response=  "Deleted Sucessfully";
		}
		Gson gson=new Gson();
		return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
	}

	/*
	 * Update a Student
	 * 
	*/
	@ApiOperation(value="Update Student")
	@RequestMapping(value="/updateStudent/{id}",method = RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStudent(@RequestBody StudentRequestDTO studentDTO,
			@PathVariable String id) throws StudentException {


		StudentResponseDTO studentResponseDTO = studentService.update(studentDTO, id);

		
		if(Objects.nonNull(studentResponseDTO)) {
			throw new StudentException("Student Id not match");
		}
		
		return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
	}


	/**
	 * Handle request to download an Excel document
	 * @throws StudentException 
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ExcelView download(Model model) throws StudentException {
		
	    model.addAttribute("students", studentService.getAllStudents());
	    return new ExcelView();
	}
	
}
