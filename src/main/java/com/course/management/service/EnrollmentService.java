package com.course.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.management.repository.EnrollmentRepo;

@Service
public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepo enrollmentRepo;
	
	
	

}
