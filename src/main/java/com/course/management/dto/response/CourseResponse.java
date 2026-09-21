package com.course.management.dto.response;

import java.util.UUID;

public record CourseResponse(
		UUID id,
		String name,
		Integer capacity,
		Integer enrolled_count,
		UUID instructor_id,
		String instructor_name
) {}
