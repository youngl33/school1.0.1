package com.school.service;

import com.school.dtoObject.Evaluation;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EvaluationServiceTest {

    @Autowired
    private EvaluationService evaluationService;

    @Test
    public void create() {
        Evaluation evaluation = new Evaluation();
        evaluation.setElaId(KeyUtils.uniqueKey());
        evaluation.setTeacherId("123123");
        evaluation.setStudentId("123123");
        evaluation.setSubjectName("vc+");
        evaluation.setElaGrade(10);
        evaluation.setElaAssess("safasfafd");
        evaluation.setScheduleSemester("2018-2019-01");
        Evaluation result = evaluationService.create(evaluation);
        Assert.assertNotNull(result);
    }

    @Test
    public void createAll() {
        List<Evaluation> evaluationList = new ArrayList<>();
        Evaluation evaluation = new Evaluation();
        evaluation.setElaId(KeyUtils.uniqueKey());
        evaluation.setTeacherId("2");
        evaluation.setStudentId("3");
        evaluation.setSubjectName("vc++");
        evaluation.setElaGrade(10);
        evaluation.setElaAssess("sa1fasfaf2d");
        evaluation.setScheduleSemester("2018-2019-01");
        evaluationList.add(evaluation);
        List<Evaluation> result = evaluationService.createAll(evaluationList);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findById() {
        Evaluation result = evaluationService.findById("1549116686779589839");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStudentId() {
        List<Evaluation> evaluationList = evaluationService.findByStudentId("123123");
        Assert.assertNotEquals(0,evaluationList.size());
    }

    @Test
    public void findByTeacherId() {
        List<Evaluation> evaluationList = evaluationService.findByTeacherId("123123");
        Assert.assertNotEquals(0,evaluationList.size());
    }

    @Test
    public void findOrderByElaGradeAsc(){
        PageRequest request = PageRequest.of(0,10);
        Page<Evaluation> evaluationPage = evaluationService.findOrderByElaGradeAsc(request);
        Assert.assertNotEquals(0,evaluationPage.getTotalElements());
    }

    @Test
    public void findOrderByElaGradeDesc(){
        PageRequest request = PageRequest.of(0,10);
        Page<Evaluation> evaluationPage = evaluationService.findOrderByElaGradeDesc(request);
        Assert.assertNotEquals(0,evaluationPage.getTotalElements());
    }
}