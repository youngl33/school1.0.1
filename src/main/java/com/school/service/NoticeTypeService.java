package com.school.service;

import com.school.dtoObject.NoticeType;

public interface NoticeTypeService {

    /** 通过类别名查找类别信息     */
    public NoticeType findByNtypeName(String ntypeName);
}
