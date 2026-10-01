package com.course.management.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@CreatedBy
//	private String createdBy;
//	@LastModifiedBy
//	private String lastModifiedBy;
//	@CreatedDate
//	private CreatedDate createdDate;
//	@LastModifiedDate
//	private LastModifiedDate lastModifiedDate;
	
	
	public BaseEntity() {super();}
	public BaseEntity(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public String getCreatedBy() {
//		return createdBy;
//	}
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//	public String getLastModifiedBy() {
//		return lastModifiedBy;
//	}
//	public void setLastModifiedBy(String lastModifiedBy) {
//		this.lastModifiedBy = lastModifiedBy;
//	}
//	public CreatedDate getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(CreatedDate createdDate) {
//		this.createdDate = createdDate;
//	}
//	public LastModifiedDate getLastModifiedDate() {
//		return lastModifiedDate;
//	}
//	public void setLastModifiedDate(LastModifiedDate lastModifiedDate) {
//		this.lastModifiedDate = lastModifiedDate;
//	}
	
	
	
}
