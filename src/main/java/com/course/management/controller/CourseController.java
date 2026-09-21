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

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.entity.Course;
import com.course.management.mapper.CourseMapper;
import com.course.management.service.CourseService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/course/{id}")
	public CourseResponse getCourse(@PathVariable UUID id){
		return courseService.getCourse(id);
	}
	
	@GetMapping("/course/{name}")
	public List<CourseResponse> getCourseByName(@PathVariable String name){
		return courseService.getCourse(name);
	}
	
	@GetMapping
	public List<CourseResponse> getAllCourses(){
		return courseService.getAllCourse();
	}

	@PostMapping("/add")
	public ResponseEntity<CourseResponse> addCourse(@RequestBody @Valid CourseRequest courseRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequest));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CourseResponse> updateCourse(@PathVariable UUID id, @RequestBody @Valid CourseRequest courseRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id,courseRequest));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCourse(@PathVariable UUID id) {
		courseService.deleteCourse(id);
	}
	
}
