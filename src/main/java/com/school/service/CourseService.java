package com.school.service;

import com.school.dtoObject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {

    /**查找所有课程*/
    Page<Course> findAll(Pageable pageable);

    /**创建*/
    Course save(Course course);

    /**查找单条信息*/
    Course findOne(String courseId);

    /**通过学年查找信息*/
    Page<Course> findByScheduleSemester(Pageable pageable,String scheduleSemester);

    /**通过教师Id查找课信息*/
    Page<Course> findByTeacherId(Pageable pageable,String teacherId);

    /**通过学年和教师Id查找课信息*/
    Page<Course> findByScheduleSemesterAndTeacherId(Pageable pageable,String scheduleSemester,String teacherId);

    /**查找起始周在所加课程周内的课程*/
    List<Course> findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(Integer courseBegin,Integer courseEnd);

    /**查找节课周在所加课程周内的课程*/
    List<Course> findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(Integer courseBegin,Integer courseEnd);

    /**查找起始周和节课周包括了所加课程周的课程*/
    List<Course> findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(Integer courseBegin,Integer courseEnd);

    /**通过科目id查找课程*/
    Page<Course> findBySubjectId(Pageable pageable,String subjectId);

    /**通过学期和科目id查找课程*/
    Page<Course> findByScheduleSemesterAndSubjectId(Pageable pageable,String scheduleSemester,String subjectId);

    /**通过科目id和教师id查找课程*/
    Page<Course> findBySubjectIdAndTeacherId(Pageable pageable,String subjectId,String teahcerId);

    /**通过学期和科目id和教师id查找课程*/
    Page<Course> findByScheduleSemesterAndSubjectIdAndTeacherId(Pageable pageable,String scheduleSemester,String subjectId,String teacherId);

    List<Course> findByTeacherId(String teacherId);
}
