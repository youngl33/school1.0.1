package com.school.service;
import com.school.dtoObject.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    /**通过教师姓名和教师状态和所在学院查找教师信息*/
    Page<Teacher> findByTeacherNameContainingAndTeacherStatusAndAinfoId(Pageable pageable,String teacherName,String teacherStatus,String ainfoId);

    /**通过教师姓名和教师状态查询教师信息*/
    Page<Teacher> findByTeacherNameContainingAndTeacherStatus(Pageable pageable,String teacherName,String teacherStatus);

    /** 通过教师ID查找教师信息*/
    Teacher findOne(String teacherId);

    /**通过模糊查询老师姓名来查找某些老师信息*/
    List<Teacher> findByTeacherNameContaining(String teacherName);

    /** 保存一个老师的信息*/
    Teacher save(Teacher teacher);

    /** 通过一个老师的名字来查找老师信息*/
    List<Teacher> findByTeacherName(String teacherName);

    /** 查找所有教师的信息*/
    Page<Teacher> findAll(Pageable pageable);

    /** 通过状态查找教师信息*/
    Page<Teacher> findByTeacherStatus(Pageable pageable,String teacherStatus);

    /** 通过学院id和教师状态查找教师信息*/
    Page<Teacher> findByAcademyIdAndTeacherStatus(Pageable pageable,String ainfoId,String  teacherStatus);

    /** 通过教师职位查找教师信息*/
    Page<Teacher> findByTeacherPosition(Pageable pageable,String teacherPosition);

    /** 通过教师职位和状态查找教师信息*/
    Page<Teacher> findByTeacherPositionAndTeacherStatus(Pageable pageable,String teacherPositioin,String teacherStatus);

    /** 通过教师所在学院ID和教师职称和教师状态查找教师信息*/
    Page<Teacher> findByAinfoIdAndTeacherPositionAndTeacherStatus(Pageable pageable,String ainfoId,String teacherPosition,String teacherStatus);

}
