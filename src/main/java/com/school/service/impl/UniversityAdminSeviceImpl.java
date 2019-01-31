package com.school.service.impl;

import com.school.dtoObject.UniversityAdmin;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.repository.UniversityAdminRepository;
import com.school.service.UniversityAdminSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityAdminSeviceImpl implements UniversityAdminSevice {

    @Autowired
    private UniversityAdminRepository universityAdminRepository;

    @Override
    public UniversityAdmin create(UniversityAdmin universityAdmin) {
        return universityAdminRepository.save(universityAdmin);
    }

    @Override
    public UniversityAdmin findOne(Integer id) {
        return universityAdminRepository.findById(id).orElse(null);
    }

    @Override
    public UniversityAdmin findByUserName(String userName) {
        return universityAdminRepository.findByUadmUsername(userName);
    }

    @Override
    public List<UniversityAdmin> findAll() {
        return universityAdminRepository.findAll();
    }

    @Override
    public void delete(Integer uadmId) {
        UniversityAdmin universityAdmin = findOne(uadmId);
        if(universityAdmin==null){
            throw new AdminException(ResultEnum.ADMIN_UN_NOT_EXIST);
        }
        try {
            universityAdminRepository.delete(universityAdmin);
        } catch (Exception e) {
            throw new AdminException(999, e.getMessage());
        }
    }
}
