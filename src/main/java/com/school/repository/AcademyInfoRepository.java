package com.school.repository;

import com.school.dtoObject.Academyinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyInfoRepository extends JpaRepository<Academyinfo,Integer> {
}
