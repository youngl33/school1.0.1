package com.school.service;

import com.school.dtoObject.AcademyInfo;


import java.util.List;

public interface AcademyInfoService {

    /**查询一个学院信息     */
    AcademyInfo findOne(String ainfoId);

    /** 保存学院的信息     */
    AcademyInfo save(AcademyInfo academyInfo);

    /**通过学院名字查找学院信息      */
    AcademyInfo findByAinfoName(String ainfoName);

    /**查找所有学院的信息          */
    List<AcademyInfo> findAll();

}
