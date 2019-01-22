package com.school.service;

import com.school.dtoObject.AcademyAdmin;

import java.util.List;

public interface AcademyAdminService {
    /**查询所有学院管理员信息*/
    List<AcademyAdmin> findAll();

    /**保存管理员信息*/
    AcademyAdmin save(AcademyAdmin academyAdmin);

    /**查找一个管理员信息*/
    AcademyAdmin findOne(Integer aadmId);
}
