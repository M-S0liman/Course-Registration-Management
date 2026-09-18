package com.course.management.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;

@Entity
public class Instructor {

	@Id
	@UuidGenerator
	private UUID id;
	private String name;
	private String phoneNum;
	@Email
	private String email;
	@OneToMany(mappedBy = "instructor",cascade = CascadeType.REMOVE)
	private List<Course>courses;
	
	public Instructor() {super();}
	public Instructor(UUID id, String name, String phoneNum, @Email String email) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
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
