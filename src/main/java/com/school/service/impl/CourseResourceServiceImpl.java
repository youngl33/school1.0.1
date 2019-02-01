package com.school.service.impl;


import com.school.dtoObject.CourseResource;
import com.school.repository.CourseResourceRepository;
import com.school.service.CourseResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseResourceServiceImpl implements CourseResourceService {

    @Autowired
    private CourseResourceRepository courseResourceRepository;

    @Override
    public CourseResource create(CourseResource courseResource) {
        return courseResourceRepository.save(courseResource);
    }

    @Override
    public CourseResource findById(String id) {
        return courseResourceRepository.findById(id).orElse(null);
    }

    @Override
    public Page<CourseResource> findByCourseId(Pageable pageable, String courseId) {
        return courseResourceRepository.findByCourseId(pageable,courseId);
    }

}
