package com.school.repository;

import com.school.dtoObject.AcademyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyInfoRepository extends JpaRepository<AcademyInfo,String> {

   AcademyInfo findByAinfoName(String AInfoName);

}
