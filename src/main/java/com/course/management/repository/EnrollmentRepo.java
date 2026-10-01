package com.course.management.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long>{

	List<Enrollment> findByStudentId(Long student_id);
	List<Enrollment> findByCourseId(Long course_id);
}
