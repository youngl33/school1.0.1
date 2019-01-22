package com.school.repository;

import com.school.dtoObject.AcademyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyAdminRepository extends JpaRepository<AcademyAdmin,Integer> {
    AcademyAdmin findByAinfoId(String ainfoId);
}
