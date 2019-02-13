package com.school.repository;

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


@RunWith(SpringRunner.class)
@SpringBootTest
public class EvaluationRepositoryTest {

    @Autowired
    private EvaluationRepository repository;

    @Test
    public void create(){
        Evaluation evaluation = new Evaluation();
        evaluation.setElaId(KeyUtils.uniqueKey());
        evaluation.setTeacherId("123123");
        evaluation.setStudentId("123123");
        evaluation.setSubjectName("vc+");
        evaluation.setElaGrade(10);
        evaluation.setElaAssess("safasfafd");
        evaluation.setScheduleSemester("2018=2019-01");
        Evaluation result = repository.save(evaluation);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        Evaluation result = repository.findById("1549116686779589839").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStudentId(){
        List<Evaluation> evaluationList = repository.findByStudentId("123123");
        Assert.assertNotEquals(0,evaluationList.size());
    }

    @Test
    public void findByTeacherId(){
        List<Evaluation> evaluationList = repository.findByTeacherId("123123");
        Assert.assertNotEquals(0,evaluationList.size());
    }

    @Test
    public void findOrderByElaGradeAsc(){
        PageRequest request = PageRequest.of(0,10);
        Page<Evaluation> evaluationPage = repository.findByOrderByElaGradeAsc(request);
        Assert.assertNotEquals(0,evaluationPage.getTotalElements());
    }

    @Test
    public void findOrderByElaGradeDesc(){
        PageRequest request = PageRequest.of(0,10);
        Page<Evaluation> evaluationPage = repository.findByOrderByElaGradeDesc(request);
        Assert.assertNotEquals(0,evaluationPage.getTotalElements());
    }
}