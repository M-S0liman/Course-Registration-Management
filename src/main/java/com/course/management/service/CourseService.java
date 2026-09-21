package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.entity.Course;
import com.course.management.entity.Instructor;
import com.course.management.mapper.CourseMapper;
import com.course.management.mapper.InstructorMapper;
import com.course.management.repository.CourseRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private InstructorService instructorService;
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private InstructorMapper instructorMapper;
	
	//CRUD ---------------------------
	
	@Transactional
	public CourseResponse createCourse(CourseRequest courseRequest) {
		//capacity must be > 0
		if(courseRequest.capacity() <= 0) {
			throw new IllegalArgumentException("Capacity must be more than 0");
		}
		//instructor must be exist
		Instructor ins = instructorMapper.toEntity(instructorService.getInstructor(courseRequest.instructor_id()));
		
		Course c = courseMapper.toEntity(courseRequest);
		c.setInstructor(ins);
		
		courseRepo.save(c);
		
		return courseMapper.toResponse(c);
	}
	
	public CourseResponse getCourse(UUID id) {
		Course c = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course with id:"+id+" not found"));
		return courseMapper.toResponse(c);
	}
	
	public List<CourseResponse> getCourse(String name) {
		return courseRepo.findByName(name)
				.stream()
				.map(courseMapper::toResponse)
				.toList();
	}
		
	public List<CourseResponse> getAllCourse(){
		return courseRepo.findAll()
				.stream()
				.map(courseMapper::toResponse)
				.toList();
	}
	
	@Transactional
	public CourseResponse updateCourse(UUID id, CourseRequest courseRequest) {
		//check course exist
		Course old = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found to update"));
		//check capacity
		Integer cap = courseRequest.capacity();
		if(cap <= 0 || old.getEnrolledCount() > cap) {
			throw new IllegalArgumentException("capacity must be more than or equal student already enrolled");
		}
		//check instructor exist
		Instructor ins = instructorMapper.toEntity(instructorService.getInstructor(courseRequest.instructor_id()));
		
		old.setName(courseRequest.name());
		old.setCapacity(cap);
		old.setEnrolledCount(courseRequest.enrolled_count());
		old.setInstructor(ins);
		
		Course saved = courseRepo.save(old);
		
		return courseMapper.toResponse(saved);
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
