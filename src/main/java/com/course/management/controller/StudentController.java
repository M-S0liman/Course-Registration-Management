package com.course.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;

}
