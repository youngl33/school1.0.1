package com.school.service.impl;

import com.school.dtoObject.CourseDetail;
import com.school.repository.CourseDetailRepository;
import com.school.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    @Autowired
    private CourseDetailRepository repository;

    @Override
    public CourseDetail save(CourseDetail courseDetail) {
        return repository.save(courseDetail);
    }

    @Override
    public CourseDetail findOne(String courseDtlId) {
        return repository.findById(courseDtlId).orElse(null);
    }

    @Override
    public Page<CourseDetail> findByClassroomId(Pageable pageable, Integer classroomId) {
        return repository.findByClassroomId(pageable,classroomId);
    }

    @Override
    public List<CourseDetail> findByCourseId(String courseId) {
        return repository.findByCourseId(courseId);
    }

 /*   @Override
    public List<CourseDetail> findByCourseBeginGreaterThanEqualAndCourseEndLessThanEqual(Integer courseBegin, Integer courseEnd) {
        return repository.;
    }*/
}
