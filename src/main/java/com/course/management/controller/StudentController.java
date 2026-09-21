package com.course.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.dto.request.StudentRequest;
import com.course.management.dto.response.StudentResponse;
import com.course.management.entity.Student;
import com.course.management.mapper.StudentMapper;
import com.course.management.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student-id/{id}")
	public StudentResponse getStudent(@PathVariable UUID id){
		return studentService.getStudent(id);
	}
	@GetMapping("/student-email/{email}")
	public StudentResponse getStudent(@PathVariable @Email String email){
		return  studentService.getStudent(email);
	}
	
	@GetMapping("/student-name/{name}")
	public List<StudentResponse> getStudentByName(@PathVariable String name) {
	    return studentService.getStudentByName(name);
	}
	
	@GetMapping
	public List<StudentResponse> getAllStudent(){
		return studentService.getAllStudent();
	}

	@PostMapping("/add")
	public ResponseEntity<StudentResponse> addStudent(@RequestBody @Valid StudentRequest studentRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudnet(studentRequest));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentResponse> updateStudent(@PathVariable UUID id, @RequestBody @Valid StudentRequest studentRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id,studentRequest));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable UUID id) {
		studentService.deleteSudent(id);
	}
	
}
