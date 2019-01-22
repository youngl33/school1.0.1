package com.school.repository;

import com.school.dtoObject.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax,String> {

    Page<Tax> findByScheduleSemester(Pageable pageable, String scheduleSemester);

    Page<Tax> findByScheduleSemesterAndTeacherName(Pageable pageable,String scheduleSemester, String teacherName);

    Page<Tax> findByScheduleSemesterAndSubjectNameContaining(Pageable pageable,String scheduleSemester,String subjectName);

    Page<Tax> findByScheduleSemesterAndSubjectNameContainingAndTeacherName(Pageable pageable,String scheduleSemester,String subjectName,String teacherName);


}
