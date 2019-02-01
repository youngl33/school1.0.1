package com.school.service.impl;

import com.school.dtoObject.Subject;
import com.school.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SubjectServiceImplTest {

    @Autowired
    private SubjectService subjectService;

    @Test
    public void create() {
        Subject subject=new Subject();
        subject.setSubjectId("1040502003");
        subject.setSubjectName("大学英语III");
        subject.setAinfoId("1040502");
        subject.setSubjectDescription("是1621807的学位课");
        Subject result=subjectService.save(subject);
        Assert.assertNotNull(result);
    }

    @Test
    public void findBySubjectNameContainning() {
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=subjectService.findBySubjectNameContainning(request,"大学");
        log.info("【找到的数量】：Number={}",subjectPage.getTotalElements());
        Assert.assertNotEquals(0,subjectPage.getTotalElements());

    }

    @Test
    public void findByAinfoId() {
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=subjectService.findByAinfoId(request,"1040501");
        Assert.assertNotEquals(0,subjectPage.getTotalElements());
    }

    @Test
    public void findAll() {
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> result=subjectService.findAll(request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void findByAinfoNameAndSubjectName() {
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=subjectService.findByAinfoIdAndSubjectNameContaining(request,"1040501","操作");
        Assert.assertNotEquals(0,subjectPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Subject subject=subjectService.findOne("1040501001");
        Assert.assertNotNull(subject);
    }

    @Test
    public void deleteById(){
       subjectService.delete("1040501002");
    }

    @Test
    public void findBySubjectName(){
        Subject subject=subjectService.findBySubjectName("操作系统原理");
        Assert.assertNotNull(subject);
    }
}