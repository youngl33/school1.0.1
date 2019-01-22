package com.school.service.impl;

import com.school.dtoObject.AcademyAdmin;
import com.school.repository.AcademyAdminRepository;
import com.school.service.AcademyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyAdminServiceImpl implements AcademyAdminService {

    @Autowired
    private AcademyAdminRepository repository;

    @Override
    public List<AcademyAdmin> findAll() {
        return repository.findAll();
    }

    @Override
    public AcademyAdmin save(AcademyAdmin academyAdmin) {
        return repository.save(academyAdmin);
    }

    @Override
    public AcademyAdmin findOne(Integer aadmId){
        return  repository.findById(aadmId).orElse(null);
    }
}
