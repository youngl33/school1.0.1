package com.school.service;

import com.school.dtoObject.CourseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseDetailService {

    /**创建一个课程详细信息*/
    CourseDetail save(CourseDetail courseDetail);

    /**查找一条信息*/
    CourseDetail findOne(String courseDtlId);

    /**通过教室Id查找信息*/
    Page<CourseDetail> findByClassroomId(Pageable pageable,Integer classroomId);

    /**通过课程Id查找信息*/
    List<CourseDetail> findByCourseId(String courseId);

}
