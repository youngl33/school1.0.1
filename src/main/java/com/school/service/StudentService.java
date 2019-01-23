package com.school.service;

import com.school.dtoObject.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface StudentService {

    Student create(Student student);

    Student findById(String studentId);

    Page<Student> findByStudentNameContaining(Pageable pageable,String studentName);


    Page<Student> findAll(Pageable pageable);

    Page<Student> findByClassId(Pageable pageable,String classID);

    void importStudent(String fileName, MultipartFile file) throws Exception;

    void delete(String studentId);
}
