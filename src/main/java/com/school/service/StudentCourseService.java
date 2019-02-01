package com.school.service;

import com.school.dtoObject.StudentCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentCourseService{

    /**创建一个学生课程*/
    StudentCourse save(StudentCourse studentCourse);

    /**查找一个学生课程*/
    StudentCourse findOne(String scourseId);

    /**通过学生id查找学生课程*/
    Page<StudentCourse> findByStudentId(Pageable pageable,String studentId);

    /**通过学年信息查找学生课程*/
    Page<StudentCourse> findByScheduleSemester(Pageable pageable,String scheduleSemester);

    /**通过学年信息和学生id查找学生课程*/
    Page<StudentCourse> findByScheduleSemesterAndStudentId(Pageable pageable,String scheduleSemester,String studentId);

    /**通过课程id查找学生课程*/
    Page<StudentCourse> findByCourseId(Pageable pageable,String courseId);

    /**通过学年信息和课程id查找学生课程*/
    Page<StudentCourse> findByScheduleSemesterAndCourseId(Pageable pageable,String scheduleSemester,String courseId);
}
