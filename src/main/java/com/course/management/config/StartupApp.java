package com.course.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.course.management.entity.Course;
import com.course.management.entity.Enrollment;
import com.course.management.entity.EnrollmentStatus;
import com.course.management.entity.Instructor;
import com.course.management.entity.Student;
import com.course.management.service.CourseService;
import com.course.management.service.EnrollmentService;
import com.course.management.service.InstructorService;
import com.course.management.service.StudentService;

@Component
public class StartupApp implements CommandLineRunner{
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private InstructorService instructorService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollmentService enrollmentService;

	@Override
	public void run(String... args) throws Exception {
		// Student
		Student s1 = new Student();
		s1.setName("mahmoud");
		s1.setEmail("m@c.com");
		s1.setPhoneNum("111111");
		
		Student s2 = new Student();
		s2.setName("soliman");
		s2.setEmail("s@c.com");
		s2.setPhoneNum("222222");
		
		studentService.createStudnet(s1);
		studentService.createStudnet(s2);
		
		//Instructor
		Instructor i1 = new Instructor();
		i1.setName("ahmed");
		i1.setEmail("a@c.com");
		i1.setPhoneNum("111111");
		
		Instructor i2 = new Instructor();
		i2.setName("omar");
		i2.setEmail("o@c.com");
		i2.setPhoneNum("222222");
		
		instructorService.createInstructor(i1);
		instructorService.createInstructor(i2);
		
		//Course
		Course c1 = new Course();
		c1.setInstructor(i1);
		c1.setName("java");
		c1.setCapacity(100);
		
		Course c2 = new Course();
		c2.setInstructor(i2);
		c2.setName("os");
		c2.setCapacity(100);
		
		courseService.createCourse(c1);
		courseService.createCourse(c2);
		
		//Enrollment
		enrollmentService.enrollStudentIntoCourse(s1.getId(), c1.getId());
		enrollmentService.enrollStudentIntoCourse(s2.getId(), c2.getId());
	}

	
}
