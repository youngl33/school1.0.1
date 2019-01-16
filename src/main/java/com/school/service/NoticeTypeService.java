package com.school.service;

import com.school.dtoObject.NoticeType;

import java.util.List;

public interface NoticeTypeService {

    /** 通过类别名查找类别信息     */
    public NoticeType findByNtypeName(String ntypeName);

    /** 保存类别信息           */
    public NoticeType save(NoticeType noticeType);

    /** 通过id查找类别         */
    public NoticeType findByNtypeId(Integer ntypeId);

    /** 查找所有类别       */
    public List<NoticeType> findAll();
}
