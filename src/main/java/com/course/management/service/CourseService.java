package com.course.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.entity.Course;
import com.course.management.entity.Instructor;
import com.course.management.exception.RecordNotFoundException;
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
	
	public CourseResponse getCourse(Long id) {
		Optional<Course> c = courseRepo.findById(id);
		if(!c.isPresent()) {
			throw new RecordNotFoundException("Course with id:"+id+" Not Found");
		}
		return courseMapper.toResponse(c.get());
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
	public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
		//check course exist
		Optional<Course> old = courseRepo.findById(id);
		if(!old.isPresent()) {
			throw new RecordNotFoundException("Course with id:"+id+" Not Found");
		}
		//check capacity
		Integer cap = courseRequest.capacity();
		if(cap <= 0 || old.get().getEnrolledCount() > cap) {
			throw new IllegalArgumentException("capacity must be more >= "+old.get().getEnrolledCount()+" (students already enrolled)");
		}
		//check instructor exist
		Instructor ins = instructorMapper.toEntity(instructorService.getInstructor(courseRequest.instructor_id()));
		
		old.get().setName(courseRequest.name());
		old.get().setCapacity(cap);
		old.get().setEnrolledCount(courseRequest.enrolled_count());
		old.get().setInstructor(ins);
		
		Course saved = courseRepo.save(old.get());
		
		return courseMapper.toResponse(saved);
	}
	
	public void deleteCourse(Long id) {
		Optional<Course> c = courseRepo.findById(id);
		if(!c.isPresent()) {
			throw new RecordNotFoundException("Course with id:"+id+" Not Found");
		}
		
		// if there are students enrolled  cancel all enrollments  or prevent deleting course	
		if(c.get().getEnrolledCount() > 0) {
			throw new IllegalArgumentException("cannot delete course with id:"+id+" because there are students enrolled");
		}
		
		courseRepo.delete(c.get());
	}
	//------------------------------------CRUD
}
