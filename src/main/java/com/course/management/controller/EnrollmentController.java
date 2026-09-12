package com.course.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.service.EnrollmentService;

@RestController
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
}
