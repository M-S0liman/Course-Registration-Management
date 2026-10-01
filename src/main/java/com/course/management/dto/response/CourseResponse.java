package com.course.management.dto.response;


public record CourseResponse(
		Long id,
		String name,
		Integer capacity,
		Integer enrolled_count,
		Long instructor_id,
		String instructor_name
) {}
