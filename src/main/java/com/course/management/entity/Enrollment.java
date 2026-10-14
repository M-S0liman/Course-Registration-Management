package com.course.management.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Enrollment extends BaseEntity{

	private EnrollmentStatus enrollmentStatus;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	public Enrollment(Long id, Student student, Course course) {
		super(id);
		this.student = student;
		this.course = course;
	}
	
}
