package com.school.service;

import com.school.dtoObject.NoticeType;

import java.util.List;

public interface NoticeTypeService {

    /** 通过类别名查找类别信息     */
    NoticeType findByNtypeName(String ntypeName);

    /** 保存类别信息           */
    NoticeType save(NoticeType noticeType);

    /** 通过id查找类别         */
    NoticeType findByNtypeId(Integer ntypeId);

    /** 查找所有类别       */
    List<NoticeType> findAll();


}
