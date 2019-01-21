package com.school.service.impl;

import com.school.dtoObject.Student;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> findByStudentName(String studentName) {
        return studentRepository.findByStudentNameContaining(studentName);
    }

    @Override
    public List<Student> findByClassId(String classId) {
        return studentRepository.findByClassId(classId);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByStatus(Pageable pageable, String status) {
        return studentRepository.findByStudentStatus(pageable,status);
    }
}
