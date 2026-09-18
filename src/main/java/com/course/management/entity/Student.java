package com.course.management.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

	@Id
	@UuidGenerator
	private UUID id;
	@NotBlank
	private String name;
	@Email
	private String email;
	private String phoneNum;
	@OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)
	private List<Enrollment> enrollments;
	
	
	public Student() {super();}
	public Student(UUID id, String name, @Email String email, String phoneNum) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
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
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
