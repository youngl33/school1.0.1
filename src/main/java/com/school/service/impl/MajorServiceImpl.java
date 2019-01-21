package com.school.service.impl;

import com.school.dtoObject.Major;
import com.school.repository.MajorRepositiory;
import com.school.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepositiory majorRepositiory;

    @Override
    public Major create(Major major) {
        return majorRepositiory.save(major);
    }

    @Override
    public Major findById(String majorId) {
        return majorRepositiory.findById(majorId).orElse(null);
    }

    @Override
    public Major findByMajorName(String majorName) {
        return majorRepositiory.findByMajorName(majorName);
    }

    @Override
    public List<Major> findByAinfoId(String ainfoId) {
        return majorRepositiory.findByAinfoId(ainfoId);
    }

    @Override
    public Page<Major> findAll(Pageable pageable) {
        return majorRepositiory.findAll(pageable);
    }
}
