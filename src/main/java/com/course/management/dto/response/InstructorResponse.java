package com.course.management.dto.response;

import java.util.UUID;

public record InstructorResponse(

		UUID id,
		String name,
		String email,
		String phoneNum
		
) {}
