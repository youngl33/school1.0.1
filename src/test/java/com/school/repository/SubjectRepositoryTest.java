package com.school.repository;

import com.school.dtoObject.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository repository;

    @Test
    public void create(){
        Subject subject=new Subject();
        subject.setSubjectId("1040502002");
        subject.setSubjectName("大学英语II");
        subject.setAinfoId("1040502");
        subject.setSubjectDescription("是1621807的学位课");
        Subject result=repository.save(subject);
        Assert.assertNotNull(result);

    }

    @Test
    public void findBySubjectNameContaining(){
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=repository.findBySubjectNameContaining(request,"大学");
        log.info("【找到的数量】：Number={}",subjectPage.getTotalElements());
        Assert.assertNotEquals(0,subjectPage.getTotalElements());
    }

    @Test
    public void findByAinfoId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=repository.findByAinfoId(request,"1040501");
        Assert.assertNotEquals(0,subjectPage.getTotalElements());
    }

    @Test
    public void findByAinfoIdAndSubjectNameContaining(){
        PageRequest request=PageRequest.of(0,10);
        Page<Subject> subjectPage=repository.findByAinfoIdAndSubjectNameContaining(request,"1040501","操作");
        Assert.assertNotEquals(0,subjectPage.getTotalElements());
    }

    @Test
    public void delete(){
        repository.deleteById("1040501001");
    }

    @Test
    public void findBySubjectName(){
        Subject subject=repository.findBySubjectName("操作系统原理");
        Assert.assertNotNull(subject);
    }

}