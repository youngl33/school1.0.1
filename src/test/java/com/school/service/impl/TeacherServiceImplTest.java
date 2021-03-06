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
import org.springframework.data.domain.Pageable;
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
        teacher.setAinfoId("1040503");
        teacher.setTeacherAvater("null");
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter("1974-01-11"));
        teacher.setTeacherCareer("职业记录良好");
        teacher.setTeacherDescription("null");
        teacher.setTeacherIcard("360502197401110932");
        teacher.setTeacherId("200709002");
        teacher.setTeacherGender("男");
        teacher.setTeacherName("廖小洋");
        teacher.setTeacherPassword("123456");
        teacher.setTeacherPosition("高校讲师");
        teacher.setTeacherStatus("在职");
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
        PageRequest request = PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findAll(request);
        log.info("【查找的数量】,Number={}",((Page) teacherPage).getTotalElements());
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherStatus(request,"在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherPosition(){
        PageRequest request= PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherPosition(request,"高校教授");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherPositionAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherPositionAndTeacherStatus(request,"高校教授","在职");
        log.info("【数量】:Number={}",teacherPage.getTotalElements());
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByAinfoIdAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByAcademyIdAndTeacherStatus(request,"1040503","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }
    @Test
    public  void findByAinfoIdAndTeacherPositionAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByAinfoIdAndTeacherPositionAndTeacherStatus(request,"1040503","高校讲师","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Teacher teacher=teacherService.findOne("200809002");
        Assert.assertNotNull(teacher);
    }

    @Test
    public void findByTeacherNameContaining(){
        List<Teacher> teacherList=teacherService.findByTeacherNameContaining("夏");
        Assert.assertNotEquals(0,teacherList.size());
    }

    @Test
    public void findByTeacherNameContainingAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherNameContainingAndTeacherStatus(request,"夏","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherNameAndTeacherStatusAndAinfoId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=teacherService.findByTeacherNameContainingAndTeacherStatusAndAinfoId(request,"夏","在职","1040502");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }


}