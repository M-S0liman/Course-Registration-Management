package com.course.management.dto.request;

import java.util.UUID;

public record EnrollmentRequest(
		
		UUID student_id,
		UUID course_id
		
) {}
