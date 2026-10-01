package com.course.management.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Instructor extends BaseEntity{

	@NotBlank
	private String name;
	private String phoneNum;
	@Email
	private String email;
	@OneToMany(mappedBy = "instructor",cascade = CascadeType.REMOVE)
	private List<Course>courses;
	
	public Instructor() {super();}
	public Instructor(Long id, String name, String phoneNum, @Email String email) {
		super(id);
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
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
