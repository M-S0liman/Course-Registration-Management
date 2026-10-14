package com.course.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.service.InstructorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {

	private final InstructorService instructorService;

	
	@GetMapping("/instructor-id/{id}")
	public ResponseEntity<InstructorResponse> getInstructor(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.getInstructor(id));
	}
	@GetMapping("/instructor-email/{email}")
	public ResponseEntity<InstructorResponse> getInstructor(@PathVariable @Email String email){
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.getInstructor(email));
	}
	
	@GetMapping("/instructor-name/{name}")
	public ResponseEntity<List<InstructorResponse>> getInstructorByName(@PathVariable String name){
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.getInstructorByName(name));
	}

	@GetMapping
	public ResponseEntity<List<InstructorResponse>> getAllInstructors(){
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.getAllInstructors());
	}
	
	@PostMapping("/add")
	public ResponseEntity<InstructorResponse> addInstructor(@RequestBody @Valid InstructorRequest instructor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.createInstructor(instructor));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<InstructorResponse> updateInstructor(@PathVariable Long id, @RequestBody @Valid InstructorRequest instructor) {
		return ResponseEntity.status(HttpStatus.OK).body(instructorService.updateInstructor(id,instructor));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteInstructor(@PathVariable Long id) {
		instructorService.deleteInstructor(id);
	}
	
}
