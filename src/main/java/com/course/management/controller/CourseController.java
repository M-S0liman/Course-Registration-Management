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

import com.course.management.entity.Course;
import com.course.management.service.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/course/{id}")
	public Course getCourse(@PathVariable UUID id){
		return courseService.getCourse(id);
	}
	
	@GetMapping("/course/{name}")
	public List<Course> getCourseByName(@PathVariable String name){
		return courseService.getCourse(name);
	}

	@PostMapping("/add")
	public Course addCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@PutMapping("/update")
	public Course updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCourse(@PathVariable UUID id) {
		courseService.deleteCourse(id);
	}
	
}
