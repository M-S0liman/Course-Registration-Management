package com.course.management.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

	@Id
	@UuidGenerator
	private UUID id;
	private String name;
	private Integer capacity;
	private Integer enrolledCount=0;
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	@OneToMany(mappedBy = "course",cascade = CascadeType.REMOVE)
	private List<Enrollment>enrollment;
	
	
	public Course() {super();}
	public Course(UUID id, String name, Integer capacity, Integer enrolledCount, Instructor instructor) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.enrolledCount = enrolledCount;
		this.instructor = instructor;
	}



	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getEnrolledCount() {
		return enrolledCount;
	}
	public void setEnrolledCount(Integer enrolledCount) {
		this.enrolledCount = enrolledCount;
	}
	
}
