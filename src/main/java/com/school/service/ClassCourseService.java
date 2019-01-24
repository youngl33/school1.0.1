package com.school.service;

import com.school.dtoObject.Class;
import com.school.dtoObject.ClassCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassCourseService {

    /**创建一个课表*/
    ClassCourse save(ClassCourse classCourse);

    /**查找一个课表*/
    ClassCourse findOne(String ccourseId);

    /**删除一个课表*/
    void delete(String ccourseId);

    /**查找所有课表*/
    Page<ClassCourse> findAll(Pageable pageable);

    /**通过班级Id查找课表*/
    Page<ClassCourse> findByClassId(Pageable pageable,String classId);

    /**通过学年信息查找课表*/
    Page<ClassCourse> findByScheduleSemester(Pageable pageable,String scheduleSemester);

    /**通过学年信息和班级号查找课表*/
    Page<ClassCourse> findByScheduleSemesterAndClassId(Pageable pageable,String scheduleSemester,String classId);

}
