package com.course.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long>{

	List<Course> findByName(String name);
}
