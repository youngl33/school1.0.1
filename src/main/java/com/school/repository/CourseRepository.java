package com.school.repository;

import com.school.dtoObject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course,String> {

    Page<Course> findByScheduleSemester(Pageable pageable, String ScheduleSemester);

    Page<Course> findByTeacherId(Pageable pageable,String teacherId);

    Page<Course> findBySubjectId(Pageable pageable,String subjectId);

    Page<Course> findByScheduleSemesterAndSubjectId(Pageable pageable,String scheduleSemester,String subjectId);

    Page<Course> findBySubjectIdAndTeacherId(Pageable pageable,String subjectId,String teacherId);

    Page<Course> findByScheduleSemesterAndSubjectIdAndTeacherId(Pageable pageable,String scheduleSemester,String subjectId,String teacherId);

    Page<Course> findByScheduleSemesterAndTeacherId(Pageable pageable,String scheduleSemester,String teacherId);

    List<Course> findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(Integer courseBegin, Integer courseEnd);

    List<Course> findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(Integer courseBegin,Integer courseEnd);

    List<Course> findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(Integer coursBegin,Integer courseEnd);
}