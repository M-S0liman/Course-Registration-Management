package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.entity.Course;
import com.course.management.repository.CourseRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private InstructorService instructorService;
	
	//CRUD ---------------------------
	
	@Transactional
	public Course createCourse(Course course) {
		//capacity must be > 0
		if(course.getCapacity() <= 0) {
			throw new IllegalArgumentException("Capacity must be more than 0");
		}
		//instructor must be exist
		instructorService.getInstructor(course.getInstructor().getId());
		
		return courseRepo.save(course);
	}
	
	public Course getCourse(UUID id) {
		return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course with id:"+id+" not found"));
	}
	
	public List<Course> getCourse(String name) {
		return courseRepo.findByName(name);
	}
		
	public List<Course> getAllCourse(){
		return courseRepo.findAll();
	}
	
	@Transactional
	public Course updateCourse(Course course) {
		//check course exist
		Course old = courseRepo.findById(course.getId()).orElseThrow(() -> new RuntimeException("Course not found to update"));
		//check capacity
		Integer cap = course.getCapacity();
		if(cap <= 0 || old.getEnrolledCount() > cap) {
			throw new IllegalArgumentException("capacity must be more than or equal student already enrolled");
		}
		//check instructor exist
		instructorService.getInstructor(course.getInstructor().getId());
		
		return courseRepo.save(course);
	}
	
	public void deleteCourse(UUID id) {
		Course c = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course with id:"+id+" not found to delete"));
		
		// if there are students enrolled  cancel all enrollments  or prevent deleting course	
		if(c.getEnrolledCount() > 0) {
			throw new IllegalArgumentException("cannot delete this course because there are students enrolled");
		}
		
		courseRepo.delete(c);
	}
	//------------------------------------CRUD
}
