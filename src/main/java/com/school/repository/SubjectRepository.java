package com.school.repository;

import com.school.dtoObject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<Subject,String> {

    Subject findBySubjectName(String subjectName);

    Page<Subject> findBySubjectNameContaining(Pageable pageable, String subjectName);

    Page<Subject> findByAinfoId(Pageable pageable,String ainfoId);

    Page<Subject> findByAinfoIdAndSubjectNameContaining(Pageable pageable,String ainfoId,String subjectName);
}
