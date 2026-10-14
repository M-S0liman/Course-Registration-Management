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
@Getter
@Setter
public class Student extends BaseEntity {

	private String name;
	private String email;
	private String phoneNum;
	@OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)
	private List<Enrollment> enrollments;
	
	
	public Student(Long id, String name, @Email String email, String phoneNum) {
		super(id);
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
	}
	
}
