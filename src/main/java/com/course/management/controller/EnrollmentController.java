package com.course.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.management.dto.request.EnrollmentRequest;
import com.course.management.dto.response.EnrollmentResponse;
import com.course.management.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@GetMapping("/enrollment/{id}")
	public EnrollmentResponse getEnrollment(@PathVariable Long id) {
		return enrollmentService.getEnrollment(id);
	}
	@GetMapping("/stud/{student_id}")
	public List<EnrollmentResponse> getStudentEnrollments(@PathVariable UUID student_id){
		return enrollmentService.getStudentEnrollments(student_id);
	}
	@GetMapping("/course/{course_id}")
	public List<EnrollmentResponse> getCourseEnrollments(@PathVariable UUID course_id){
		return enrollmentService.getCourseEnrollments(course_id);
	}
	
	@PostMapping("/add")
	public EnrollmentResponse createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
		return enrollmentService.enrollStudentIntoCourse(enrollmentRequest);
	}
	
	@PutMapping("/drop/{enroll_id}")
	public EnrollmentResponse dropCourse(@PathVariable Long enroll_id) {
		return enrollmentService.dropCourse(enroll_id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEnrollment(@PathVariable Long id) {
		enrollmentService.deleteEnrollment(id);
	}
}
