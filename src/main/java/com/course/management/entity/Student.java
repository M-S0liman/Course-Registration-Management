package com.course.management.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity
public class Student {

	@Id
	private UUID id;
	private String name;
	@Email
	private String email;
	private String phoneNum;
	private Double gpa;
	
	
	public Student() {super();}
	
	
	public Student(UUID id, String name, @Email String email, String phoneNum, Double gpa) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.gpa = gpa;
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
	public Double getGpa() {
		return gpa;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
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
