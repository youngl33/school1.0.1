package com.school.service;
import com.school.dtoObject.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    /** 保存一个老师的信息*/
    public Teacher save(Teacher teacher);

    /** 通过一个老师的名字来查找老师信息*/
    public List<Teacher> findByTeacherName(String teacherName);

    /** 查找所有老师的信息*/
    public Page findAll(Pageable pageable);

/*    *//** 删除一个老师的信息*//*
    public void delete(String teacherId);*/
}
