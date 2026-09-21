package com.course.management.dto.response;

import java.util.UUID;

import com.course.management.entity.EnrollmentStatus;

public record EnrollmentResponse(
		
		Long enrollment_id,
		EnrollmentStatus status,
		UUID student_id,
		UUID course_id
) {}
