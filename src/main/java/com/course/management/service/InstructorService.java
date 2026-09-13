package com.course.management.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.entity.Instructor;
import com.course.management.repository.InstructorRepo;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepo instructorRepo;
	
	//CRUD ---------------------------
		public Instructor getInstructor(UUID id) {
			return instructorRepo.findById(id).orElseThrow(() -> new RuntimeException("Instructor with id:"+id+" not found"));
		}
		
		public Instructor getInstructor(String email) {
			
			return instructorRepo.findByEmail(email);
		}
		
		public Instructor getInstructorByName(String name) {
			return instructorRepo.findByName(name);
		}
		
		public List<Instructor> getAllInstructors(){
			return instructorRepo.findAll();
		}
		
		public Instructor createInstructor(Instructor instructor) {
			if(instructorRepo.existsByEmail(instructor.getEmail())){
				throw new IllegalArgumentException("Email already exist");
			}
			return instructorRepo.save(instructor);
		}
		
		@Transactional
		public Instructor updateInstructor(Instructor instructor) {
			Instructor old = instructorRepo.findById(instructor.getId()).orElseThrow(() -> new RuntimeException("instructor not found"));
			
			//check email 
			if(instructor.getEmail()!= old.getEmail() && instructorRepo.existsByEmail(instructor.getEmail())) {
				throw new IllegalArgumentException("Email already exist");
			}
			old.setName(instructor.getName());
			old.setEmail(instructor.getEmail());
			old.setPhoneNum(instructor.getPhoneNum());
			
			return instructorRepo.save(instructor);
		}
		
		public void deleteInstructor(UUID id) {
			Instructor s = instructorRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
			instructorRepo.delete(s);
		}
		//------------------------------------CRUD
	
}
