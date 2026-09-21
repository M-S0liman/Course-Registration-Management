package com.course.management.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
		
		@NotBlank(message = "Course name is required")
		String name,
		
		@NotNull
		Integer capacity,
		
		Integer enrolled_count,
		
		UUID instructor_id
) {}
