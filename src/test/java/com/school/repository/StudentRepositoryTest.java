package com.school.repository;

import com.school.dtoObject.Student;
import com.school.utils.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    public void create() throws ParseException {
        Student student = new Student();
        student.setStudentId("201620180732");
        student.setStudentPassword("000000");
        student.setStudentGender("女");
        student.setStudentAddr("江西省新余市");
        student.setStudentName("余若盈");
        student.setStudentIcard("360732111111111112");
        student.setStudentBorndate(DateFormatUtils.dateConverter("1998-12-15"));
        student.setMajorName("软件工程");
        student.setAinfoName("软件学院");
        student.setStudentAvater("123123");
        student.setStudentTel("12345678910");
        student.setClassId("1621807");
        Student result = repository.save(student);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        Student result = repository.findById("201620180713").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByClassId(){
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentPage = repository.findByClassId(request,"1621807");
        Assert.assertNotEquals(0,studentPage.getTotalElements());
    }

    @Test
    public void findByStudentName(){
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentPage = repository.findByStudentNameContaining(request,"赖");
        Assert.assertNotEquals(0,studentPage.getTotalElements());
    }

    @Test
    public void findByAll(){
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentList = repository.findBy(request);
        Assert.assertNotEquals(0,studentList.getTotalElements());
    }


}