package com.course.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.entity.Enrollment;
import com.course.management.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@GetMapping("/enrollment/{id}")
	public Enrollment getEnrollment(@PathVariable Long id) {
		return enrollmentService.getEnrollment(id);
	}
	@GetMapping("/stud/{student_id}")
	public List<Enrollment> getStudentEnrollments(@PathVariable UUID student_id){
		return enrollmentService.getStudentEnrollments(student_id);
	}
	@GetMapping("/course/{course_id}")
	public List<Enrollment> getCourseEnrollments(@PathVariable UUID course_id){
		return enrollmentService.getCourseEnrollments(course_id);
	}
	
	@PostMapping("/add/{stud_id}/{course_id}")
	public Enrollment createEnrollment(@PathVariable UUID stud_id,@PathVariable UUID course_id) {
		return enrollmentService.enrollStudentIntoCourse(stud_id, course_id);
	}
	
	@PutMapping("/drop/{enroll_id}")
	public Enrollment dropCourse(@PathVariable Long enroll_id) {
		return enrollmentService.dropCourse(enroll_id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEnrollment(@PathVariable Long id) {
		enrollmentService.deleteEnrollment(id);
	}
}
