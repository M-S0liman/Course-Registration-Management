package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.StudentRequest;
import com.course.management.dto.response.EnrollmentResponse;
import com.course.management.dto.response.StudentResponse;
import com.course.management.entity.Enrollment;
import com.course.management.entity.Student;
import com.course.management.mapper.StudentMapper;
import com.course.management.repository.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private EnrollmentService enrollmentService;
	@Autowired
	private StudentMapper studentMapper;
	
	//CRUD ---------------------------
	public StudentResponse getStudent(UUID id) {
		Student s = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
		return studentMapper.toResponse(s);
	}
	
	public StudentResponse getStudent(String email) {
		Student s = studentRepo.findByEmail(email);
		return studentMapper.toResponse(s);
	}
	
	public List<StudentResponse> getStudentByName(String name) {
		return studentRepo.findByName(name)
				.stream()
				.map(studentMapper::toResponse)
				.toList();
	}
	
	public List<StudentResponse> getAllStudent(){
		return studentRepo.findAll()
				.stream()
				.map(studentMapper::toResponse)
				.toList();
	}
	
	public StudentResponse createStudnet(StudentRequest studentRequest) {
		if(studentRepo.existsByEmail(studentRequest.email())){
			throw new IllegalArgumentException("Email already exist");
		}

		Student s = studentMapper.toEntity(studentRequest);
		
		studentRepo.save(s);
		
		return studentMapper.toResponse(s);
	}
	@Transactional
	public StudentResponse updateStudent(UUID id, StudentRequest studentRequest) {
		
		Student old = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
		
		//check email 
		if(! (studentRequest.email().equals(old.getEmail())) && studentRepo.existsByEmail(studentRequest.email())) {
			throw new IllegalArgumentException("Email already exist");
		}
		old.setName(studentRequest.name());
		old.setEmail(studentRequest.email());
		old.setPhoneNum(studentRequest.phoneNum());
	
		Student saved = studentRepo.save(old);
		
		return studentMapper.toResponse(saved);
	}
	
	
	public void deleteSudent(UUID id) {
		Student s = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
		List<EnrollmentResponse> stud_enrollments = enrollmentService.getStudentEnrollments(id);
		for (EnrollmentResponse en : stud_enrollments) {
			enrollmentService.deleteEnrollment(en.enrollment_id());
		}
		studentRepo.delete(s);
	}
	
	//------------------------------------CRUD
	
	
	
	

}
