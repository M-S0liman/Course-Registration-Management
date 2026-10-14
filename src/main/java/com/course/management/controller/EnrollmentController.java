package com.course.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	
	@GetMapping("/enrollment/{id}")
	public ResponseEntity<EnrollmentResponse> getEnrollment(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollment(id));
	}
	@GetMapping("/stud/{student_id}")
	public ResponseEntity<List<EnrollmentResponse>> getStudentEnrollments(@PathVariable Long student_id){
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getStudentEnrollments(student_id));
	}
	@GetMapping("/course/{course_id}")
	public ResponseEntity<List<EnrollmentResponse>> getCourseEnrollments(@PathVariable Long course_id){
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getCourseEnrollments(course_id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<EnrollmentResponse> createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudentIntoCourse(enrollmentRequest));
	}
	
	@PutMapping("/drop/{enroll_id}")
	public ResponseEntity<EnrollmentResponse> dropCourse(@PathVariable Long enroll_id) {
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.dropCourse(enroll_id));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEnrollment(@PathVariable Long id) {
		enrollmentService.deleteEnrollment(id);
	}
}
