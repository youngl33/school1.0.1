package com.school.service;

import com.school.dtoObject.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EvaluationService {

    /** 创建 */
    Evaluation create(Evaluation evaluation);

    /** 创建多个  */
    List<Evaluation> createAll(List<Evaluation> evaluations);

    /** 通过id查找  */
    Evaluation findById(String id);

    /** 通过学生id查找  */
    List<Evaluation> findByStudentId(String studentId);

    /** 通过教师id查找  */
    List<Evaluation> findByTeacherId(String teacherId);

    /** 通过评分升序查找 */
    Page<Evaluation> findOrderByElaGradeAsc(Pageable pageable);

    /** 通过评分降序查找 */
    Page<Evaluation> findOrderByElaGradeDesc(Pageable pageable);
}
