package com.course.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.entity.Instructor;
import com.course.management.service.InstructorService;

import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/instructor-id/{id}")
	public Instructor getInstructor(@PathVariable UUID id){
		return instructorService.getInstructor(id);
	}
	@GetMapping("/instructor-email/{email}")
	public Instructor getInstructor(@PathVariable @Email String email){
		return instructorService.getInstructor(email);
	}
	
	@GetMapping("/instructor-name/{name}")
	public List<Instructor> getInstructorByName(@PathVariable String name){
		return instructorService.getInstructorByName(name);
	}

	@PostMapping("/add")
	public Instructor addInstructor(@RequestBody Instructor instructor) {
		return instructorService.createInstructor(instructor);
	}
	
	@PutMapping("/update")
	public Instructor updateInstructor(@RequestBody Instructor instructor) {
		return instructorService.updateInstructor(instructor);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteInstructor(@PathVariable UUID id) {
		instructorService.deleteInstructor(id);
	}
	
}
