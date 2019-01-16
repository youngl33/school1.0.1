package com.school.repository;

import com.school.dtoObject.NoticeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeTypeRepository extends JpaRepository<NoticeType,Integer> {

    NoticeType findByNtypeName(String ntypeName);

}
