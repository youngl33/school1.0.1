package com.school.service.impl;

import com.school.dtoObject.Teacher;
import com.school.service.TeacherService;
import com.school.utils.DateFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.engine.TemplateHandlerAdapterMarkupHandler;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TeacherServiceImplTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void create()throws Exception{
        Teacher teacher = new Teacher();
        teacher.setTeacherAddr("江西省南昌市经济技术开发区蓝天花园");
        teacher.setAinfoId("1040501");
        teacher.setTeacherAge(45);
        teacher.setTeacherAvater("null");
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter("1974-01-11"));
        teacher.setTeacherCareer("职业记录良好");
        teacher.setTeacherDescription("null");
        teacher.setTeacherIcard("360502197401110932");
        teacher.setTeacherId("104050101");
        teacher.setTeacherGender("男");
        teacher.setTeacherName("廖小洋");
        teacher.setTeacherPassword("123456");
        teacher.setTeacherPosition("搞笑讲师");
        teacher.setTeacherStatus(0);
        teacher.setTeacherTel("18979065210");
        Teacher result = teacherService.save(teacher);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByTeacherName(){
        List<Teacher> result=teacherService.findByTeacherName("廖小洋");
        log.info("【通过教师名字查找】,number={}",result.size());
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findAll(){
        PageRequest request = new PageRequest(0,10);
        Page<Teacher> teacherPage=teacherService.findAll(request);
        log.info("【查找的数量】,Number={}",((Page) teacherPage).getTotalElements());
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherStatus(){
        PageRequest request=new PageRequest(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherStatus(request,0);
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherPosition(){
        PageRequest request= new PageRequest(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherPosition(request,"高校教授");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }


}