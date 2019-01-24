package com.school.repository;

import com.school.dtoObject.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class,String> {

    List<Class> findByMajorId(String majorId);
}
