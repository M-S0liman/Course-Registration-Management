package com.course.management.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Instructor extends BaseEntity{

	private String name;
	private String email;
	private String phoneNum;
	@OneToMany(mappedBy = "instructor",cascade = CascadeType.REMOVE)
	private List<Course>courses;
	
	public Instructor(Long id, String name, String phoneNum, @Email String email) {
		super(id);
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
	}	
	
}
