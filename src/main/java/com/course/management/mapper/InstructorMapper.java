package com.course.management.mapper;

import org.springframework.stereotype.Component;

import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.entity.Instructor;

@Component
public class InstructorMapper {
	
	public Instructor toEntity(InstructorRequest req) {
		Instructor i = new Instructor();
		i.setName(req.name());
		i.setEmail(req.email());
		i.setPhoneNum(req.phoneNum());
		return i;
	}

	public Instructor toEntity(InstructorResponse res) {
		Instructor i = new Instructor();
		i.setId(res.id());
		i.setName(res.name());
		i.setEmail(res.email());
		i.setPhoneNum(res.phoneNum());
		return i;
	}
	
	public InstructorRequest toRequest(Instructor i) {
		return new InstructorRequest(i.getName(), i.getEmail(), i.getPhoneNum());
	}
	
	
	public InstructorResponse toResponse(Instructor i) {
		return new InstructorResponse(i.getId(),i.getName(),i.getEmail(),i.getPhoneNum());
	}

}
