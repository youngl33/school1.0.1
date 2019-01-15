package com.school.service;

import com.school.dtoObject.AcademyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademyInfoService {

    /**查询一个学院信息     */
    public AcademyInfo findOne(String ainfoId);

    /** 保存学院的信息     */
    public AcademyInfo save(AcademyInfo academyInfo);

    /**通过学院名字查找学院信息      */
    public AcademyInfo findByAinfoName(String ainfoName);

    /**查找所有学院的信息          */
    public List<AcademyInfo> findAll();

}
