package com.school.repository;

import com.school.dtoObject.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,String> {

    Page<Student> findBy(Pageable pageable);

    Page<Student> findByClassId(Pageable pageable,String classId);

    Page<Student> findByStudentNameContaining(Pageable pageable,String studentName);
}
