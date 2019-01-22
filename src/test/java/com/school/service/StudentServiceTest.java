package com.school.service;

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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void create() throws ParseException {
        Student student = new Student();
        student.setStudentId("201620180713");
        student.setStudentPassword("000000");
        student.setStudentGender("男");
        student.setStudentAddr("江西省赣州市");
        student.setStudentName("赖家洋");
        student.setStudentIcard("360732111111111111");
        student.setStudentBorndate(DateFormatUtils.dateConverter("1997-9-9"));
        student.setMajorName("软件工程");
        student.setAinfoName("软件学院");
        student.setStudentAvater("123123");
        student.setStudentTel("12345678910");
        student.setClassId("1621807");
        Student result = studentService.create(student);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        Student result = studentService.findById("201620180732");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStudentName() {
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentList = studentService.findByStudentNameContaining(request,"赖");
        Assert.assertNotEquals(0,studentList.getTotalElements());
    }



    @Test
    public void findByClassId(){
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentList = studentService.findByClassId(request,"1621807");
        Assert.assertNotEquals(0,studentList.getTotalElements());
    }
}