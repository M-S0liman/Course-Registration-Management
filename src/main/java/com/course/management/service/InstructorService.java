package com.course.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.management.repository.InstructorRepo;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepo instructorRepo;
	
	
}
