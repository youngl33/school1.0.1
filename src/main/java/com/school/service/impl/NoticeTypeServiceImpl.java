package com.school.service.impl;

import com.school.dtoObject.NoticeType;
import com.school.repository.NoticeTypeRepository;
import com.school.service.NoticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeTypeServiceImpl implements NoticeTypeService {

    @Autowired
    private NoticeTypeRepository repository;

    @Override
    public NoticeType findByNtypeName(String ntypeName) {
        return repository.findByNtypeName(ntypeName);
    }
}
