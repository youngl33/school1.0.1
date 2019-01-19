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
    public Page<Teacher> findByTeacherStatus(Pageable pageable, String  teacherStatus) {
        return repository.findByTeacherStatus(pageable, teacherStatus);
    }

    @Override
    public Page<Teacher> findByAcademyIdAndTeacherStatus(Pageable pageable,String ainfoId,String  teacherStatus){
        return repository.findByAinfoIdAndTeacherStatus(pageable,ainfoId,teacherStatus);
    }

    @Override
    public Page<Teacher> findByTeacherPosition(Pageable pageable, String teacherPosition) {
        return repository.findByTeacherPosition(pageable,teacherPosition);
    }

    @Override
    public Page<Teacher> findByTeacherPositionAndTeacherStatus(Pageable pageable, String teacherPositioin, String  teacherStatus) {
        return repository.findByTeacherPositionAndTeacherStatus(pageable,teacherPositioin,teacherStatus);
    }

    @Override
    public Page<Teacher> findByAinfoIdAndTeacherPositionAndTeacherStatus(Pageable pageable, String ainfoId, String teacherPosition,String  teacherStatus) {
        return repository.findByAinfoIdAndTeacherPositionAndTeacherStatus(pageable,ainfoId,teacherPosition,teacherStatus);
    }

    @Override
    public Teacher findOne(String teacherId){
        return repository.findById(teacherId).orElse(null);
    }

    @Override
    public List<Teacher> findByTeacherNameContaining(String teacherName) {
        return repository.findAllByTeacherNameContaining(teacherName);
    }
}
