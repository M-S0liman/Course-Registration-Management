package com.course.management.mapper;

import org.springframework.stereotype.Component;

import com.course.management.dto.request.StudentRequest;
import com.course.management.dto.response.StudentResponse;
import com.course.management.entity.Student;

@Component
public class StudentMapper {
	
	
	public Student toEntity(StudentRequest req) {
		Student s = new Student();
		s.setName(req.name());
		s.setEmail(req.email());
		s.setPhoneNum(req.phoneNum());
		return s;
	}

	public Student toEntity(StudentResponse res) {
		Student s = new Student();
		s.setId(res.id());
		s.setName(res.name());
		s.setEmail(res.email());
		s.setPhoneNum(res.phoneNum());
		return s;
	}
	
	public StudentRequest toRequest(Student s) {
		return new StudentRequest(s.getName(), s.getEmail(), s.getPhoneNum());
	}
	
	public StudentResponse toResponse(Student s) {
		return new StudentResponse(s.getId(), s.getName(), s.getEmail(), s.getPhoneNum());
	}
}
