package com.school.repository;

import com.school.dtoObject.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepositiory extends JpaRepository<Major,String> {

    List<Major> findByAinfoId(String ainfoId);

    Major findByMajorName(String majorName);
}
