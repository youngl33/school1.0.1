package com.school.service.impl;

import com.school.dtoObject.Class;
import com.school.repository.ClassRepository;
import com.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class create(Class classdto) {
        return classRepository.save(classdto);
    }

    @Override
    public Class find(String classId) {
        return classRepository.findById(classId).orElse(null);
    }

    @Override
    public List<Class> findByMajorId(String majorId) {
        return classRepository.findByMajorId(majorId);
    }
}
