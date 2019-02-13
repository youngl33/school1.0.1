package com.school.repository;

import com.school.dtoObject.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, String> {

    List<Evaluation> findByStudentId(String studentId);

    List<Evaluation> findByTeacherId(String teacherId);

    Page<Evaluation> findByOrderByElaGradeAsc(Pageable pageable);

    Page<Evaluation> findByOrderByElaGradeDesc(Pageable pageable);

}
