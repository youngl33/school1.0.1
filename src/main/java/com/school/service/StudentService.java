package com.school.service;

import com.school.dtoObject.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    Student findById(String studentId);

    List<Student> findByStudentName(String studentName);

    List<Student> findByClassId(String classId);

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByStatus(Pageable pageable,String status);
}
