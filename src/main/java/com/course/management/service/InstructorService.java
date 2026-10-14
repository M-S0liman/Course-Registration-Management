package com.course.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.entity.Instructor;
import com.course.management.exception.RecordNotFoundException;
import com.course.management.mapper.InstructorMapper;
import com.course.management.repository.InstructorRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorService {

	private final InstructorRepo instructorRepo;
	private final InstructorMapper instructorMapper;
	
	
	//CRUD ---------------------------
	public InstructorResponse getInstructor(Long id) {
		Optional<Instructor> i = instructorRepo.findById(id);
		if(!i.isPresent()) {
			throw new RecordNotFoundException("Instructor with id:"+id+" Not Found");
		}
		return instructorMapper.toResponse(i.get());
	}
		
	public InstructorResponse getInstructor(String email) {
		Optional<Instructor> i = instructorRepo.findByEmail(email);
		if(!i.isPresent()) {
			throw new RecordNotFoundException("Instructor with email:"+email+" Not Found");
		}
		return instructorMapper.toResponse(i.get()); 
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
	public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
		Optional<Instructor> old = instructorRepo.findById(id);
		if(!old.isPresent()) {
			throw new RecordNotFoundException("Instructor with id:"+id+" Not Found");
		}
			
		//check email 
		if(!instructorRequest.email().equals(old.get().getEmail()) && instructorRepo.existsByEmail(instructorRequest.email())) {
			throw new IllegalArgumentException("Email already exist");
		}
			
		old.get().setName(instructorRequest.name());
		old.get().setEmail(instructorRequest.email());
		if(instructorRequest.phoneNum() != null) {
			old.get().setPhoneNum(instructorRequest.phoneNum());
		}
			
		Instructor saved = instructorRepo.save(old.get());
			
		return instructorMapper.toResponse(saved);
	}
		
	public void deleteInstructor(Long id) {
		Optional<Instructor> i = instructorRepo.findById(id);
		if(!i.isPresent()) {
			throw new RecordNotFoundException("Instructor with id:"+id+" Not Found");
		}
		instructorRepo.delete(i.get());
	}
		//------------------------------------CRUD
	
}
