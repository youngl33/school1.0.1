package com.school.service.impl;

import com.school.dtoObject.Course;
import com.school.repository.CourseRepository;
import com.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public Page<Course> findByScheduleSemester(Pageable pageable, String scheduleSemester) {
        return repository.findByScheduleSemester(pageable, scheduleSemester);
    }

    @Override
    public Page<Course> findByTeacherId(Pageable pageable, String teacherId) {
        return repository.findByTeacherId(pageable, teacherId);
    }

    @Override
    public Page<Course> findByScheduleSemesterAndTeacherId(Pageable pageable, String scheduleSemester, String teacherId) {
        return repository.findByScheduleSemesterAndTeacherId(pageable,scheduleSemester,teacherId);
    }

    @Override
    public Course findOne(String courseId) {
        return repository.findById(courseId).orElse(null);
    }

    @Override
    public List<Course> findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(Integer courseBegin, Integer courseEnd) {
        return repository.findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(courseBegin,courseEnd);
    }

    @Override
    public List<Course> findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(Integer courseBegin, Integer courseEnd) {
        return repository.findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(courseBegin,courseEnd);
    }

    @Override
    public List<Course> findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(Integer courseBegin, Integer courseEnd) {
        return repository.findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(courseBegin,courseEnd);
    }

    @Override
    public Page<Course> findBySubjectId(Pageable pageable, String subjectId) {
        return repository.findBySubjectId(pageable,subjectId);
    }

    @Override
    public Page<Course> findByScheduleSemesterAndSubjectId(Pageable pageable, String scheduleSemester, String subjectId) {
        return repository.findByScheduleSemesterAndSubjectId(pageable,scheduleSemester,subjectId);
    }

    @Override
    public Page<Course> findBySubjectIdAndTeacherId(Pageable pageable, String subjectId, String teahcerId) {
        return repository.findBySubjectIdAndTeacherId(pageable,subjectId,teahcerId);
    }

    @Override
    public Page<Course> findByScheduleSemesterAndSubjectIdAndTeacherId(Pageable pageable, String scheduleSemester, String subjectId, String teacherId) {
        return repository.findByScheduleSemesterAndSubjectIdAndTeacherId(pageable,scheduleSemester,subjectId,teacherId);
    }
}


