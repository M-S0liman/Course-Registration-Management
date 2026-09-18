package com.course.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, UUID>{

	List<Instructor> findByName(String name);
	Instructor findByEmail(String Email);
	boolean existsByEmail(String email);
}
