package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.entity.Student;
import com.course.management.repository.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	//CRUD ---------------------------
	public Student getStudent(UUID id) {
		return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
	}
	
	public Student getStudent(String email) {
		return studentRepo.findByEmail(email);
	}
	
	public List<Student> getStudentByName(String name) {
		return studentRepo.findByName(name);
	}
	
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}
	
	public Student createStudnet(Student student) {
		if(studentRepo.existsByEmail(student.getEmail())){
			throw new IllegalArgumentException("Email already exist");
		}
		return studentRepo.save(student);
	}
	@Transactional
	public Student updateStudent(Student student) {
		
		Student old = studentRepo.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student not found"));
		
		//check email 
		if(student.getEmail()!= old.getEmail() && studentRepo.existsByEmail(student.getEmail())) {
			throw new IllegalArgumentException("Email already exist");
		}
		old.setName(student.getName());
		old.setEmail(student.getEmail());
		old.setPhoneNum(student.getPhoneNum());
		
		return studentRepo.save(old);
	}
	
	public void deleteSudent(UUID id) {
		Student s = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
		studentRepo.delete(s);
	}
	
	//------------------------------------CRUD
	
	
	
	

}
