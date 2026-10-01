package com.course.management.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
		
		@NotBlank(message = "Course name is required")
		String name,
		
		@NotNull(message = "capacity is required and min = 1")
		Integer capacity,
		
		Integer enrolled_count,
		@NotNull(message = "Instructor id is required")
		Long instructor_id
) {}
