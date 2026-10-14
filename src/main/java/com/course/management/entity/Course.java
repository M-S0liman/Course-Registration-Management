package com.course.management.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course extends BaseEntity{

	private String name;
	private Integer capacity;
	private Integer enrolledCount=0;
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	@OneToMany(mappedBy = "course",cascade = CascadeType.REMOVE)
	private List<Enrollment>enrollments;
	
	
	public Course(Long id, String name, Integer capacity, Integer enrolledCount, Instructor instructor) {
		super(id);
		this.name = name;
		this.capacity = capacity;
		this.enrolledCount = enrolledCount;
		this.instructor = instructor;
	}
	
}
