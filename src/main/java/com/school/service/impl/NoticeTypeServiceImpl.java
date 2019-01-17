package com.school.service.impl;

import com.school.dtoObject.NoticeType;
import com.school.repository.NoticeTypeRepository;
import com.school.service.NoticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoticeTypeServiceImpl implements NoticeTypeService {

    @Autowired
    private NoticeTypeRepository repository;

    @Override
    public NoticeType findByNtypeName(String ntypeName) {
        return repository.findByNtypeName(ntypeName);
    }

    @Override
    public NoticeType save(NoticeType noticeType) {
        return repository.save(noticeType);
    }

    @Override
    public NoticeType findByNtypeId(Integer ntypeId) {
        return repository.findById(ntypeId).orElse(null);
    }

    @Override
    public List<NoticeType> findAll() {
        return repository.findAll();
    }

}
