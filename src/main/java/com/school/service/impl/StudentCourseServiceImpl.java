package com.school.service.impl;

import com.school.dtoObject.StudentCourse;
import com.school.repository.StudentCourseRepository;
import com.school.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseRepository repository;

    @Override
    public StudentCourse save(StudentCourse studentCourse) {
        return repository.save(studentCourse);
    }

    @Override
    public StudentCourse findOne(String scourseId) {
        return repository.findById(scourseId).orElse(null);
    }

    @Override
    public Page<StudentCourse> findByStudentId(Pageable pageable, String studentId) {
        return repository.findByStudentId(pageable,studentId);
    }

    @Override
    public Page<StudentCourse> findByScheduleSemester(Pageable pageable, String scheduleSemester) {
        return repository.findByScheduleSemester(pageable,scheduleSemester);
    }

    @Override
    public Page<StudentCourse> findByScheduleSemesterAndStudentId(Pageable pageable, String scheduleSemester, String studentId) {
        return repository.findByScheduleSemesterAndStudentId(pageable,scheduleSemester,studentId);
    }

    @Override
    public Page<StudentCourse> findByCourseId(Pageable pageable, String courseId) {
        return repository.findByCourseId(pageable,courseId);
    }

    @Override
    public Page<StudentCourse> findByScheduleSemesterAndCourseId(Pageable pageable, String scheduleSemester, String courseId) {
        return repository.findByScheduleSemesterAndCourseId(pageable,scheduleSemester,courseId);
    }
}
