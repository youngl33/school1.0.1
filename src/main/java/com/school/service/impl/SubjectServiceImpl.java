package com.school.service.impl;

import com.school.dtoObject.Subject;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository repository;

    @Override
    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    @Override
    public Page<Subject> findBySubjectNameContainning(Pageable pageable, String subjectName) {
        return repository.findBySubjectNameContaining(pageable,subjectName);
    }

    @Override
    public Page<Subject> findByAinfoId(Pageable pageable, String ainfoId) {
        return repository.findByAinfoId(pageable,ainfoId);
    }

    @Override
    public Page<Subject> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Subject> findByAinfoIdAndSubjectNameContaining(Pageable pageable, String ainfoId, String subjectName) {
        return repository.findByAinfoIdAndSubjectNameContaining(pageable,ainfoId,subjectName);
    }

    @Override
    public Subject findOne(String subjectId) {
        return repository.findById(subjectId).orElse(null);
    }


    @Override
    public void delete(String subjectId) {
        repository.deleteById(subjectId);
    }

    @Override
    public Subject findBySubjectName(String subjectName) {
        return repository.findBySubjectName(subjectName);
    }
}
