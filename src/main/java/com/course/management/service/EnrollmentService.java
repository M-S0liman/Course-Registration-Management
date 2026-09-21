package com.course.management.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.request.EnrollmentRequest;
import com.course.management.dto.response.EnrollmentResponse;
import com.course.management.entity.Course;
import com.course.management.entity.Enrollment;
import com.course.management.entity.EnrollmentStatus;
import com.course.management.entity.Student;
import com.course.management.mapper.CourseMapper;
import com.course.management.mapper.EnrollmentMapper;
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
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private EnrollmentMapper enrollmentMapper;
	
//	Enrollment operations:
//	Enroll student in course.
	@Transactional
	public EnrollmentResponse enrollStudentIntoCourse(EnrollmentRequest enrollmentRequest) {

		//is this student enrolled to this course before?----------------------------------------------------------------------
		List<Enrollment> stud_enrollments = enrollmentRepo.findByStudentId(enrollmentRequest.student_id());
		Boolean isEnrolled = false;
		for (Enrollment en : stud_enrollments) {
			if(en.getCourse().getId().equals(enrollmentRequest.course_id()) && en.getEnrollmentStatus() == EnrollmentStatus.ENROLLED) {
				isEnrolled=true;
				break;
			}
		}
		if(isEnrolled) {throw new IllegalArgumentException("You already have Enrollment recorde to this course");}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrollmentStatus(EnrollmentStatus.PENDING);
		//Student must exist.
		Student student = studentRepo.findById(enrollmentRequest.student_id()).orElseThrow(() -> new RuntimeException("Student with id:"+enrollmentRequest.student_id()+" not found"));
		enrollment.setStudent(student);
		
		//Course must exist.
		Course course = courseMapper.toEntity(courseService.getCourse(enrollmentRequest.course_id()));
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
		//mapping
		CourseRequest cr = courseMapper.toRequest(course);
		courseService.updateCourse(enrollmentRequest.course_id(),cr);
		
		Enrollment saved = enrollmentRepo.save(enrollment);
		return enrollmentMapper.toResponse(saved);
	}
//	Get enrollment by ID.
	public EnrollmentResponse getEnrollment(Long id) {
		Enrollment e = enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment with id:"+id+" not found"));
		return enrollmentMapper.toResponse(e);
	}
//	Get student's enrollments.
	public List<EnrollmentResponse> getStudentEnrollments(UUID student_id){
		return enrollmentRepo.findByStudentId(student_id)
				.stream()
				.map(enrollmentMapper::toResponse)
				.toList();
	}
//	Get course's enrollments.
	public List<EnrollmentResponse> getCourseEnrollments(UUID course_id){
		return enrollmentRepo.findByCourseId(course_id)
				.stream()
				.map(enrollmentMapper::toResponse)
				.toList();
	}
	//Drop a course without delete the enrollment
	public EnrollmentResponse dropCourse(Long enrollment_id) {
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
		//mapping
		CourseRequest cr = courseMapper.toRequest(course);
		courseService.updateCourse(course.getId(),cr);
		//save update
		Enrollment saved = enrollmentRepo.save(enrollment);
		return enrollmentMapper.toResponse(saved);
	}
	
	//if enrolled drop then delete
	public void deleteEnrollment(Long id) {
		Enrollment enrollment = enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment with id:"+id+" not found to delete"));
		if(enrollment.getEnrollmentStatus() == EnrollmentStatus.ENROLLED) {
			dropCourse(id);
		}
		enrollmentRepo.deleteById(id);
	}
	//Change enrollment status.
	
	

	
}
