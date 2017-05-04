package com.vaibhav.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaibhav.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
	
}
