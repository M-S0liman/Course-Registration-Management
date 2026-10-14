package com.course.management.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.course.management.dto.request.CourseRequest;
import com.course.management.dto.request.EnrollmentRequest;
import com.course.management.dto.request.InstructorRequest;
import com.course.management.dto.request.StudentRequest;
import com.course.management.dto.response.CourseResponse;
import com.course.management.dto.response.InstructorResponse;
import com.course.management.dto.response.StudentResponse;
import com.course.management.service.CourseService;
import com.course.management.service.EnrollmentService;
import com.course.management.service.InstructorService;
import com.course.management.service.StudentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartupApp implements CommandLineRunner{
	
	private final StudentService studentService;
	private final InstructorService instructorService;
	private final CourseService courseService;
	private final EnrollmentService enrollmentService;

	@Override
	public void run(String... args) throws Exception {
		// Student
		StudentRequest sr1 = new StudentRequest("mahmoud", "m@c.com", "111111");
		StudentRequest sr2 = new StudentRequest("soliman", "s@c.com", "222222");
		
		StudentResponse st1 = studentService.createStudnet(sr1);
		StudentResponse st2 = studentService.createStudnet(sr2);
		
		//Instructor
		InstructorRequest i1 = new InstructorRequest("ahmed","a@c.com","111111");
		InstructorRequest i2 = new InstructorRequest("omar","o@c.com","222222");
		
		InstructorResponse ires1 = instructorService.createInstructor(i1);
		InstructorResponse ires2 = instructorService.createInstructor(i2);
		
		//Course
		CourseRequest c1 = new CourseRequest("java",100,0,ires1.id());
		CourseRequest c2 = new CourseRequest("os",100,0,ires2.id());
		
		CourseResponse cr1 = courseService.createCourse(c1);
		CourseResponse cr2 = courseService.createCourse(c2);
		
		//Enrollment
		EnrollmentRequest en1 = new EnrollmentRequest(st1.id(), cr1.id());
		EnrollmentRequest en2 = new EnrollmentRequest(st2.id(), cr2.id());
		enrollmentService.enrollStudentIntoCourse(en1);
		enrollmentService.enrollStudentIntoCourse(en2);
	}

	
}
