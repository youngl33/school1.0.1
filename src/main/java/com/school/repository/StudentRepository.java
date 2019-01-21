package com.school.repository;

import com.school.dtoObject.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {

    Page<Student> findByStudentStatus(Pageable pageable,String status);

    List<Student> findByClassId(String classId);

    List<Student> findByStudentNameContaining(String studentName);
}
