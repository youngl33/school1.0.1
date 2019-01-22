package com.school.service.impl;

import com.school.dtoObject.Tax;
import com.school.repository.TaxRepository;
import com.school.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository repository;

    @Override
    public Tax save(Tax tax) {
        return repository.save(tax);
    }

    @Override
    public Page<Tax> findByScheduleSemester(Pageable pageable, String scheduleSemester) {
        return repository.findByScheduleSemester(pageable,scheduleSemester);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndSubjectNameContaining(Pageable pageable, String scheduleSemester, String subjectName) {
        return repository.findByScheduleSemesterAndSubjectNameContaining(pageable,scheduleSemester,subjectName);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndTeacherName(Pageable pageable, String scheduleSemester, String teacherName) {
        return repository.findByScheduleSemesterAndTeacherName(pageable,scheduleSemester,teacherName);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndSubjectNameContainingAndTeacherName(Pageable pageable, String scheduleSemester, String subjectName, String teacherName) {
        return repository.findByScheduleSemesterAndSubjectNameContainingAndTeacherName(pageable,scheduleSemester,subjectName,teacherName);
    }

    @Override
    public Tax findOne(String taxId) {
        return repository.findById(taxId).orElse(null);
    }
}
