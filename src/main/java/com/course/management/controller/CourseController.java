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

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

	private final CourseService courseService;
	
	@GetMapping("/course-id/{id}")
	public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(id));
	}
	
	@GetMapping("/course-name/{name}")
	public ResponseEntity<List<CourseResponse>> getCourseByName(@PathVariable String name){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(name));
	}
	
	@GetMapping
	public ResponseEntity<List<CourseResponse>> getAllCourses(){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourse());
	}

	@PostMapping("/add")
	public ResponseEntity<CourseResponse> addCourse(@RequestBody @Valid CourseRequest courseRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequest));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id,courseRequest));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
	}
	
}
