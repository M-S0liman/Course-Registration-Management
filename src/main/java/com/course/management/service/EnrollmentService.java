package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.entity.Course;
import com.course.management.entity.Enrollment;
import com.course.management.entity.EnrollmentStatus;
import com.course.management.entity.Student;
import com.course.management.repository.EnrollmentRepo;

@Service
public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepo enrollmentRepo;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	
//	Enrollment operations:
//	Enroll student in course.
	@Transactional
	public Enrollment enrollStudentIntoCourse(UUID student_id,UUID course_id) {

		//is this student enrolled to this course before?----------------------------------------------------------------------
		
		
		
		
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrollmentStatus(EnrollmentStatus.PENDING);
		//Student must exist.
		Student student = studentService.getStudent(student_id);
		enrollment.setStudent(student);
		
		//Course must exist.
		Course course = courseService.getCourse(course_id);
		enrollment.setCourse(course);
		
		//Course must have available seats.
		if(course.getEnrolledCount() == course.getCapacity()) {
			enrollment.setEnrollmentStatus(EnrollmentStatus.FAILED);
		}
		enrollment.setEnrollmentStatus(EnrollmentStatus.ENROLLED);
		
		//Update the course's enrollment count.
		course.setEnrolledCount(course.getEnrolledCount()+1);
		courseService.updateCourse(course);
		
		//return
		return enrollmentRepo.save(enrollment);
	}
//	Get enrollment by ID.
	public Enrollment getEnrollment(Long id) {
		return enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment with id:"+id+" not found"));
	}
//	Get student's enrollments.
	public List<Enrollment> getStudentEnrollments(UUID student_id){
		return enrollmentRepo.findByStudentId(student_id);
	}
//	Get course's enrollments.
	public List<Enrollment> getCourseEnrollments(UUID course_id){
		return enrollmentRepo.findByStudentId(course_id);
	}
	//Drop a course without delete the enrollment
	public void dropCourse(Long enrollment_id,UUID student_id) {
		//The enrollment must exist.
		Enrollment enrollment = enrollmentRepo.findById(enrollment_id).orElseThrow(() -> new RuntimeException("Enrollment with id:"+enrollment_id+" not found to drop"));
		//The enrollment must belong to the current student.
		if(enrollment.getStudent().getId() != student_id) {
			throw new IllegalArgumentException("you cannot drop another student's enrollment");
		}
		//The enrollment must currently be ENROLLED.
		if(enrollment.getEnrollmentStatus() != EnrollmentStatus.ENROLLED) {
			throw new IllegalArgumentException("you are not Enrolled to drop");
		}
		enrollment.setEnrollmentStatus(EnrollmentStatus.DROPED);
		//After dropping, the course gets its seat back.
		Course course = enrollment.getCourse();
		course.setEnrolledCount(course.getEnrolledCount() - 1);
		courseService.updateCourse(course);
		//save update
		enrollmentRepo.save(enrollment);
	}
	//Change enrollment status.
	
	

	
}
