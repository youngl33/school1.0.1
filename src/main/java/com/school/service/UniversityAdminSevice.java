package com.school.service;

import com.school.dtoObject.UniversityAdmin;


import java.util.List;

public interface UniversityAdminSevice {

    UniversityAdmin create(UniversityAdmin universityAdmin);

    UniversityAdmin findOne(Integer id);

    UniversityAdmin findByUserName(String userName);

    List<UniversityAdmin> findAll();

    void delete(Integer uadmId);
}
