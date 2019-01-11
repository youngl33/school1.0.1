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
    public Page findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
