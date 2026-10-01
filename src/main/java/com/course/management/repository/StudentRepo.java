package com.course.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

	List<Student> findByName(String name);
	Optional<Student> findByEmail(String Email);
	boolean existsByEmail(String email);
}
