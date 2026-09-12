package com.course.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID>{

}
