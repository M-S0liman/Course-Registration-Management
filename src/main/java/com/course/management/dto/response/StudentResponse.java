package com.course.management.dto.response;

import java.util.UUID;

public record StudentResponse(
		
		UUID id,
		String name,
		String email,
		String phoneNum
		
) {}
