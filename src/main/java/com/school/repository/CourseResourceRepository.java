package com.school.repository;

import com.school.dtoObject.CourseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseResourceRepository extends JpaRepository<CourseResource,String> {

    Page<CourseResource> findByCourseId(Pageable pageable,String courseId);
}
