package com.school.service;

import com.school.dtoObject.Class;

import java.util.List;

public interface ClassService {

    /** 创建   */
    Class create(Class classdto);

    Class find(String classId);

    List<Class> findByMajorId(String majorId);
}
