package com.course.management.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.entity.Instructor;
import com.course.management.mapper.InstructorMapper;
import com.course.management.repository.InstructorRepo;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepo instructorRepo;
	@Autowired
	private InstructorMapper instructorMapper;
	
	//CRUD ---------------------------
		public InstructorResponse getInstructor(UUID id) {
			Instructor i = instructorRepo.findById(id).orElseThrow(() -> new RuntimeException("Instructor with id:"+id+" not found"));
			return instructorMapper.toResponse(i);
		}
		
		public InstructorResponse getInstructor(String email) {
			Instructor i = instructorRepo.findByEmail(email);
			return instructorMapper.toResponse(i); 
		}
		
		public List<InstructorResponse> getInstructorByName(String name) {
			return instructorRepo.findByName(name)
					.stream()
					.map(instructorMapper::toResponse)
					.toList();
		}
		
		public List<InstructorResponse> getAllInstructors(){
			return instructorRepo.findAll()
					.stream()
					.map(instructorMapper::toResponse)
					.toList();
		}
		
		public InstructorResponse createInstructor(InstructorRequest instructorRequest) {
			if(instructorRepo.existsByEmail(instructorRequest.email())){
				throw new IllegalArgumentException("Email already exist");
			}
			
			Instructor i = instructorMapper.toEntity(instructorRequest);
			
			instructorRepo.save(i);
			
			return instructorMapper.toResponse(i);
		}
		
		@Transactional
		public InstructorResponse updateInstructor(UUID id, InstructorRequest instructorRequest) {
			Instructor old = instructorRepo.findById(id).orElseThrow(() -> new RuntimeException("instructor not found"));
			
			//check email 
			if(instructorRequest.email().equals(old.getEmail()) && instructorRepo.existsByEmail(instructorRequest.email())) {
				throw new IllegalArgumentException("Email already exist");
			}
			
			old.setName(instructorRequest.name());
			old.setEmail(instructorRequest.email());
			old.setPhoneNum(instructorRequest.phoneNum());
			
			Instructor saved = instructorRepo.save(old);
			
			return instructorMapper.toResponse(saved);
		}
		
		public void deleteInstructor(UUID id) {
			Instructor s = instructorRepo.findById(id).orElseThrow(() -> new RuntimeException("Student with id:"+id+" not found"));
			instructorRepo.delete(s);
		}
		//------------------------------------CRUD
	
}
