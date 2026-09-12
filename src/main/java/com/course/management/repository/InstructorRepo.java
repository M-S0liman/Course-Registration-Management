package com.course.management.repository;

import java.lang.classfile.Instruction;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<Instruction, UUID>{

}
