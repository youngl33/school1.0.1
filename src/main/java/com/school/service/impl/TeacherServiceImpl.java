package com.school.service.impl;
import com.school.dtoObject.Teacher;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Override
    public Teacher save(Teacher teacher){
        return repository.save(teacher);
    }

    @Override
    public List<Teacher> findByTeacherName(String teacherName){
        return repository.findByTeacherName(teacherName);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Teacher> findByTeacherStatus(Pageable pageable, Integer teacherStatus) {
        return repository.findByTeacherStatus(pageable, teacherStatus);
    }

    @Override
    public Page<Teacher> findByAcademyIdAndTeacherStatus(Pageable pageable,String academyId,Integer teacherStatus){
        return repository.findByAinfoIdAndTeacherStatus(pageable,academyId,teacherStatus);
    }

    @Override
    public Page<Teacher> findByTeacherPosition(Pageable pageable, String teacherPosition) {
        return repository.findByTeacherPosition(pageable,teacherPosition);
    }

    /*   @Override
    public Page findByTeacherStatus(Pageable pageable,Integer teacherStatus)*/
}
