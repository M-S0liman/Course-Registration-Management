package com.course.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, UUID>{

	List<Course> findByName(String name);
}
