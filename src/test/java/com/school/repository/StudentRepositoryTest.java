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
        student.setStudentEnterdate(DateFormatUtils.dateConverter("2016-09-10"));
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
        List<Student> studentList = repository.findByClassId("1621807");
        Assert.assertNotEquals(0,studentList.size());
    }

    @Test
    public void findByStudentName(){
        List<Student> studentList = repository.findByStudentNameContaining("赖");
        Assert.assertNotEquals(0,studentList.size());
    }

    @Test
    public void findByStudentStatus(){
        PageRequest request = new PageRequest(0,10);
        Page<Student> studentList = repository.findByStudentStatus(request,"在读");
        Assert.assertNotEquals(0,studentList.getTotalElements());
    }
}