package com.course.management.controller;

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

import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.entity.Instructor;
import com.course.management.mapper.InstructorMapper;
import com.course.management.service.InstructorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	
	@GetMapping("/instructor-id/{id}")
	public InstructorResponse getInstructor(@PathVariable UUID id){
		return instructorService.getInstructor(id);
	}
	@GetMapping("/instructor-email/{email}")
	public InstructorResponse getInstructor(@PathVariable @Email String email){
		return instructorService.getInstructor(email);
	}
	
	@GetMapping("/instructor-name/{name}")
	public List<InstructorResponse> getInstructorByName(@PathVariable String name){
		return instructorService.getInstructorByName(name);
	}

	@GetMapping
	public List<InstructorResponse> getAllInstructors(){
		return instructorService.getAllInstructors();
	}
	
	@PostMapping("/add")
	public ResponseEntity<InstructorResponse> addInstructor(@RequestBody @Valid InstructorRequest instructor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.createInstructor(instructor));
	}
	
	@PutMapping("/update")
	public ResponseEntity<InstructorResponse> updateInstructor(@PathVariable UUID id, @RequestBody @Valid InstructorRequest instructor) {
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.updateInstructor(id,instructor));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteInstructor(@PathVariable UUID id) {
		instructorService.deleteInstructor(id);
	}
	
}
