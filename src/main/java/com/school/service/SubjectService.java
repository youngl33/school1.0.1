package com.school.service;

import com.school.dtoObject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface SubjectService {

    /**通过科目名查找科目信息*/
    Subject findBySubjectName(String subjectName);

    /**保存一个科目的信息*/
    Subject save(Subject subject);

    /**模糊查找科目名字*/
    Page<Subject> findBySubjectNameContainning(Pageable pageable,String subjectName);

    /**通过开课学院查找科目信息*/
    Page<Subject> findByAinfoId(Pageable pageable,String ainfoId);

    /**通过开课学院和科目名联合查询科目信息*/
    Page<Subject> findByAinfoIdAndSubjectNameContaining(Pageable pageable,String ainfoId,String subjectName);

    /**查找所有科目信息*/
    Page<Subject> findAll(Pageable pageable);

    /**查找一个科目的信息*/
    Subject findOne(String subjectId);

    /** 删除*/
    void delete(String subjectId);

    /**导入科目*/
    void importSubject(String fileName, MultipartFile file) throws Exception;



}
