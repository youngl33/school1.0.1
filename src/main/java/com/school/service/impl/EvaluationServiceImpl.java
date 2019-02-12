package com.school.service.impl;

import com.school.dtoObject.Evaluation;
import com.school.repository.EvaluationRepository;
import com.school.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public Evaluation create(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public List<Evaluation> createAll(List<Evaluation> evaluations) {
        return evaluationRepository.saveAll(evaluations);
    }

    @Override
    public Evaluation findById(String id) {
        return evaluationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Evaluation> findByStudentId(String studentId) {
        return evaluationRepository.findByStudentId(studentId);
    }

    @Override
    public List<Evaluation> findByTeacherId(String teacherId) {
        return evaluationRepository.findByTeacherId(teacherId);
    }

    @Override
    public Page<Evaluation> findOrderByElaGradeAsc(Pageable pageable) {
        return evaluationRepository.findByOrderByElaGradeAsc(pageable);
    }

    @Override
    public Page<Evaluation> findOrderByElaGradeDesc(Pageable pageable) {
        return evaluationRepository.findByOrderByElaGradeDesc(pageable);
    }
}
