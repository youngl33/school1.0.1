package com.school.service;

import com.school.dtoObject.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClassService {

    /** 创建   */
    Class create(Class classdto);

    Class find(String classId);

    List<Class> findByMajorId(String majorId);

    Page<Class> findAll(Pageable pageable);

    void delete(String classId);

    void importClasses(String fileName,MultipartFile file) throws Exception;
}
