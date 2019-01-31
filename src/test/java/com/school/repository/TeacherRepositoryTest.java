package com.school.repository;

import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Teacher;
import com.school.enums.TeacherStatusEnum;
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

import java.awt.print.Pageable;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private AcademyInfoRepository academyInfoRepository;

    @Test
    public void create() throws ParseException {
        Teacher teacher = new Teacher();
        teacher.setTeacherAddr("翰林世家");
        teacher.setAinfoId("1040501");
        teacher.setTeacherAge(42);
        teacher.setTeacherAvater("null");
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter("1978-01-01"));
        teacher.setTeacherCareer("无");
        teacher.setTeacherDescription("无");
        teacher.setTeacherIcard("360502197801019898");
        teacher.setTeacherId("200809002");
        teacher.setTeacherGender("女");
        teacher.setTeacherName("吴光明");
        teacher.setTeacherPassword("123456");
        teacher.setTeacherPosition("高校讲师");
        teacher.setTeacherStatus("在职");
        teacher.setTeacherTel("13388907787");
        Teacher result = repository.save(teacher);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
        repository.deleteById("11111");
    }

    @Test
    public void findByTeacherName() {
        Teacher result = repository.findById("234234").orElse(null);
        Assert.assertNotNull(result);
        System.out.print(result);
    }

    @Test
    public void findAll(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findAll(request);
        log.info("【查找的数量】,Number:{}",teacherPage.getTotalElements() );
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findByTeacherStatus(request,"在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByAcademyIdAndTeacherStatus(){
        PageRequest request= PageRequest.of(0,10);
        Page<Teacher> teacherPage= repository.findByAinfoIdAndTeacherStatus(request,"1040501","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherPosition(){
        PageRequest request = PageRequest.of(0,10);
        Page<Teacher> teacherPage= repository.findByTeacherPosition(request,"高校讲师");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherPositionAndTeacherStatus(){
        PageRequest request= PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findByTeacherPositionAndTeacherStatus(request,"高校讲师","在职");
        log.info("【查找的数量】:Number={}",teacherPage.getTotalElements());
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByAinfoIdAndTeacherPositionAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findByAinfoIdAndTeacherPositionAndTeacherStatus(request,"1040502","高校教授","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Teacher teacher=repository.findById("200809002").orElse(null);
        Assert.assertNotNull(teacher);
    }

    @Test
    public void findByTeacherNameContaining(){
        List<Teacher> teacherList=repository.findByTeacherNameContaining("夏");
        Assert.assertNotEquals(0,teacherList.size());
    }

    @Test
    public void findByTeacherNameContainingAndTeacherStatus(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findByTeacherNameContainingAndTeacherStatus(request,"夏","在职");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }

    @Test
    public void findByTeacherNameAndTeacherStatusAndAinfoId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Teacher> teacherPage=repository.findByTeacherNameContainingAndTeacherStatusAndAinfoId(request,"夏","在职","1040502");
        Assert.assertNotEquals(0,teacherPage.getTotalElements());
    }
}