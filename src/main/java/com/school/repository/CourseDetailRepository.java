package com.school.repository;

import com.school.dtoObject.CourseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDetailRepository extends JpaRepository<CourseDetail,String> {

    Page<CourseDetail> findByClassroomId(Pageable pageable,Integer classroomId);

    List<CourseDetail> findByCourseId(String courseId);



}
