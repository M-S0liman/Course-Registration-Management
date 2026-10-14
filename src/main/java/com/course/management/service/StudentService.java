package com.course.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.StudentRequest;
import com.course.management.dto.response.EnrollmentResponse;
import com.course.management.dto.response.StudentResponse;
import com.course.management.entity.Student;
import com.course.management.exception.RecordNotFoundException;
import com.course.management.mapper.StudentMapper;
import com.course.management.repository.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepo studentRepo;
	private final EnrollmentService enrollmentService;
	private final StudentMapper studentMapper;

	//CRUD ---------------------------
	public StudentResponse getStudent(Long id) {
		Optional<Student> s = studentRepo.findById(id);
		if(s.isPresent()) {
			return studentMapper.toResponse(s.get());
		}
		throw new RecordNotFoundException("Student with id:"+id+" Not Found");
	}
	
	public StudentResponse getStudent(String email) {
		Optional<Student> s = studentRepo.findByEmail(email);
		if(s.isPresent()) {
			return studentMapper.toResponse(s.get());
		}
		throw new RecordNotFoundException("Student with email:"+email+" Not Found");
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
	public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
		
		Optional<Student> old = studentRepo.findById(id);
		if(!old.isPresent()) {
			throw new RecordNotFoundException("Student with id:"+id+" Not Found"); 
		}
		
		//check email 
		if(! (studentRequest.email().equals(old.get().getEmail())) && studentRepo.existsByEmail(studentRequest.email())) {
			throw new IllegalArgumentException("Email already exist");
		}
		old.get().setName(studentRequest.name());
		old.get().setEmail(studentRequest.email());
		if(studentRequest.phoneNum() != null) {
			old.get().setPhoneNum(studentRequest.phoneNum());
		}
	
		Student saved = studentRepo.save(old.get());
		
		return studentMapper.toResponse(saved);
	}
	
	
	public void deleteSudent(Long id) {
		Optional<Student> s = studentRepo.findById(id);
		if(!s.isPresent()) {
			throw new RecordNotFoundException("Student with id:"+id+" Not Found");
		}
		List<EnrollmentResponse> stud_enrollments = enrollmentService.getStudentEnrollments(id);
		for (EnrollmentResponse en : stud_enrollments) {
			enrollmentService.deleteEnrollment(en.enrollment_id());
		}
		studentRepo.delete(s.get());
	}
	
	//------------------------------------CRUD
	
	
	
	

}
