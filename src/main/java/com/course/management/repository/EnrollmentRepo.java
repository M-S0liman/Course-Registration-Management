package com.course.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.management.entity.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long>{

}
