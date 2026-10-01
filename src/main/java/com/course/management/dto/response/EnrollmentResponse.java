package com.course.management.dto.response;


import com.course.management.entity.EnrollmentStatus;

public record EnrollmentResponse(
		
		Long enrollment_id,
		EnrollmentStatus status,
		Long student_id,
		Long course_id
) {}
