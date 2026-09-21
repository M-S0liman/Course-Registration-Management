package com.course.management.mapper;

import org.springframework.stereotype.Component;

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.entity.Course;
import com.course.management.entity.Instructor;

@Component
public class CourseMapper {

	public Course toEntity(CourseRequest req) {
		Course c = new Course();
		c.setName(req.name());
		c.setCapacity(req.capacity());
		c.setEnrolledCount(req.enrolled_count());
		return c;
	}
	
	public Course toEntity(CourseResponse res) {
		Instructor ins = new Instructor();
		ins.setId(res.instructor_id());
		ins.setName(res.instructor_name());
		
		Course c = new Course();
		c.setId(res.id());
		c.setName(res.name());
		c.setCapacity(res.capacity());
		c.setEnrolledCount(res.enrolled_count());
		c.setInstructor(ins);
		return c;
	}
	
	public CourseRequest toRequest(Course c) {
		return new CourseRequest(c.getName(), c.getCapacity(), c.getEnrolledCount(), c.getInstructor().getId());
	}
	
	public CourseResponse toResponse(Course c) {
		return new CourseResponse(c.getId(), c.getName(), c.getCapacity(), c.getEnrolledCount(), c.getInstructor().getId(), c.getInstructor().getName());
	}
}
