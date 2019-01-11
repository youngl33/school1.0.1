package com.school.service.impl;

import com.school.dtoObject.AcademyInfo;
import com.school.repository.AcademyInfoRepository;
import com.school.service.AcademyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AcademyInfoServiceImpl implements AcademyInfoService {

    @Autowired
    private AcademyInfoRepository academyInfoRepository;

    @Override
    public AcademyInfo findOne(String ainfoId) {
        return academyInfoRepository.findById(ainfoId).orElse(null);
    }

    @Override
    public AcademyInfo save(AcademyInfo academyInfo) {
        return academyInfoRepository.save(academyInfo);
    }

    @Override
    public AcademyInfo findByAinfoName(String ainfoName) {
        return academyInfoRepository.findByAinfoName(ainfoName);
    }

    @Override
    public Page<AcademyInfo> findAll(Pageable pageable) {
        return academyInfoRepository.findAll(pageable);
    }
}
