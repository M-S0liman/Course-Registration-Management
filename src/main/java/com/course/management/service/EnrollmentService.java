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
import com.course.management.repository.StudentRepo;

@Service
public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepo enrollmentRepo;
	@Autowired
	private StudentRepo studentRepo;  //repo to avoid circular references (no need student business logic in this case)
	@Autowired
	private CourseService courseService;
	
//	Enrollment operations:
//	Enroll student in course.
	@Transactional
	public Enrollment enrollStudentIntoCourse(UUID student_id,UUID course_id) {

		//is this student enrolled to this course before?----------------------------------------------------------------------
		List<Enrollment> stud_enrollments = enrollmentRepo.findByStudentId(student_id);
		Boolean isEnrolled = false;
		for (Enrollment en : stud_enrollments) {
			if(en.getCourse().getId().equals(course_id)) {
				isEnrolled=true;
				break;
			}
		}
		if(isEnrolled) {throw new IllegalArgumentException("You already have Enrollment recorde to this course");}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrollmentStatus(EnrollmentStatus.PENDING);
		//Student must exist.
		Student student = studentRepo.findById(student_id).orElseThrow(() -> new RuntimeException("Student with id:"+student_id+" not found"));
		enrollment.setStudent(student);
		
		//Course must exist.
		Course course = courseService.getCourse(course_id);
		enrollment.setCourse(course);
		
		//Course must have available seats.
		if(course.getEnrolledCount() == course.getCapacity()) {
			enrollment.setEnrollmentStatus(EnrollmentStatus.FAILED);
		}
		if(enrollment.getEnrollmentStatus().equals(EnrollmentStatus.PENDING)) {
			enrollment.setEnrollmentStatus(EnrollmentStatus.ENROLLED);
		}
		
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
		return enrollmentRepo.findByCourseId(course_id);
	}
	//Drop a course without delete the enrollment
	public Enrollment dropCourse(Long enrollment_id) {
		//The enrollment must exist.
		Enrollment enrollment = enrollmentRepo.findById(enrollment_id).orElseThrow(() -> new RuntimeException("Enrollment with id:"+enrollment_id+" not found to drop"));
		//The enrollment must belong to the current student.
//		if( ! enrollment.getStudent().getId().equals(student_id)) {
//			throw new IllegalArgumentException("you cannot drop another student's enrollment");
//		}
		
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
		return enrollment;
	}
	
	//must be not enrolled to delete///////////////////////////////////////////////////////////////////////
	public void deleteEnrollment(Long id) {
		Enrollment enrollment = getEnrollment(id);
		if(enrollment.getEnrollmentStatus() == EnrollmentStatus.ENROLLED) {
			enrollment = dropCourse(id);
		}
		enrollmentRepo.delete(enrollment);
	}
	//Change enrollment status.
	
	

	
}
