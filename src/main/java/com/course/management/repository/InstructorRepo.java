package com.course.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long>{

	List<Instructor> findByName(String name);
	Optional<Instructor> findByEmail(String Email);
	boolean existsByEmail(String email);
}
