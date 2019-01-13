package com.school.service;
import com.school.dtoObject.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    /** 保存一个老师的信息*/
    public Teacher save(Teacher teacher);

    /** 通过一个老师的名字来查找老师信息*/
    public List<Teacher> findByTeacherName(String teacherName);

    /** 查找所有教师的信息*/
    public Page<Teacher> findAll(Pageable pageable);

    /** 通过状态查找教师信息*/
    public Page<Teacher> findByTeacherStatus(Pageable pageable,Integer teacherStatus);

    /** 通过学院名和教师状态查找教师信息*/
    public Page<Teacher> findByAcademyIdAndTeacherStatus(Pageable pageable,String academyInfo,Integer teacherStatus);

    /** 通过教师职位查找教师信息*/
    public Page<Teacher> findByTeacherPosition(Pageable pageable,String teacherPosition);
    /** 删除一个老师的信息*//*
    public void delete(String teacherId);*/
}
