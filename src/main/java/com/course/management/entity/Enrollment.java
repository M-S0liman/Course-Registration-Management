package com.course.management.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {

	@Id
	private Long id;
	private EnrollmentStatus enrollmentStatus;
	@ManyToMany
	private List<Student> students;
	@ManyToOne
	private Course course;
	
	public Enrollment() {super();}
	public Enrollment(Long id, List<Student> students, Course course) {
		super();
		this.id = id;
		this.students = students;
		this.course = course;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
