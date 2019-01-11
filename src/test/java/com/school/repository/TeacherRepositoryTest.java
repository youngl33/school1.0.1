package com.school.repository;

import com.school.dtoObject.Teacher;
import com.school.utils.DateFormatUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.standard.MediaSize;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    public void create() throws ParseException {
        Teacher teacher = new Teacher();
        teacher.setTeacherAddr("23423");
        teacher.setAinfoId("1234");
        teacher.setTeacherAge(12);
        teacher.setTeacherAvater("12332131");
        teacher.setTeacherBorndate(DateFormatUtil.dateConverter("1997-09-09"));
        teacher.setTeacherCareer("dfds");
        teacher.setTeacherDescription("1212");
        teacher.setTeacherIcard("11131");
        teacher.setTeacherId("11111");
        teacher.setTeacherGender("男");
        teacher.setTeacherName("huh");
        teacher.setTeacherPassword("9878789");
        teacher.setTeacherPosition("8908");
        teacher.setTeacherStatus(0);
        teacher.setTeacherTel("89890");
        Teacher result = repository.save(teacher);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
        repository.deleteById("234234");
    }

    @Test
    public void findByTeacherName() {
        Teacher result = repository.findById("234234").orElse(null);
        Assert.assertNotNull(result);
        System.out.print(result);
    }
}