package com.school.service.impl;

import com.school.dtoObject.ClassCourse;
import com.school.repository.ClassCourseRepository;
import com.school.service.ClassCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClassCourseServiceImpl implements ClassCourseService {

    @Autowired
    private ClassCourseRepository repository;

    @Override
    public ClassCourse save(ClassCourse classCourse) {
        return repository.save(classCourse);
    }

    @Override
    public ClassCourse findOne(String ccourseId) {
        return repository.findById(ccourseId).orElse(null);
    }

    @Override
    public void delete(String ccourseId) {
        repository.deleteById(ccourseId);
    }

    @Override
    public Page<ClassCourse> findByScheduleSemester(Pageable pageable, String scheduleSemester) {
        return repository.findByScheduleSemester(pageable,scheduleSemester);
    }

    @Override
    public Page<ClassCourse> findByScheduleSemesterAndClassId(Pageable pageable, String scheduleSemester, String classId) {
        return repository.findByScheduleSemesterAndClassId(pageable,scheduleSemester,classId);
    }

    @Override
    public Page<ClassCourse> findByClassId(Pageable pageable, String classId) {
        return repository.findByClassId(pageable,classId);
    }

    @Override
    public Page<ClassCourse> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
