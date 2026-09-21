package com.course.management.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.management.dto.request.EnrollmentRequest;
import com.course.management.dto.response.EnrollmentResponse;
import com.course.management.entity.Enrollment;
import com.course.management.service.StudentService;

@Component
public class EnrollmentMapper {

	
	
//	public Enrollment toEntity(EnrollmentRequest req) {
//		Enrollment e = new Enrollment();
//		e.se
//	}
//	
	
	public EnrollmentRequest toRequest(Enrollment e) {
		return new EnrollmentRequest(e.getStudent().getId(), e.getCourse().getId());
	}
	
	public EnrollmentResponse toResponse(Enrollment e) {
		return new EnrollmentResponse(e.getId(), e.getEnrollmentStatus(), e.getStudent().getId(), e.getCourse().getId());
	}
}
