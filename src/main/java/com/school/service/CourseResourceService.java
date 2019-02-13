package com.school.service;

import com.school.dtoObject.CourseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseResourceService {

    CourseResource create(CourseResource courseResource);

    CourseResource findById(String id);

    Page<CourseResource> findByCourseId(Pageable pageable,String courseId);

    CourseResource delete(String id);
}
