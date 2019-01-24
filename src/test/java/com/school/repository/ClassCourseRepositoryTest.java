package com.school.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.school.dtoObject.ClassCourse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassCourseRepositoryTest {

    @Autowired
    private ClassCourseRepository repository;

    @Test
    public void create(){
        ClassCourse classcourse=new ClassCourse();
        classcourse.setCcourseId("162180701");
        classcourse.setClassId("1621807");
        classcourse.setScheduleSemester("2018-2019学年第一学期");
        ClassCourse result= repository.save(classcourse);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByScheduleSemester() {
        PageRequest request=new PageRequest(0,10);
        Page<ClassCourse> classCoursePage=repository.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,classCoursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndClassId() {
        PageRequest request=new PageRequest(0,10);
        Page<ClassCourse> classCoursePage=repository.findByScheduleSemesterAndClassId(request,"2018-2019学年第一学期","1621807");
        Assert.assertNotEquals(0,classCoursePage.getTotalElements());
    }

    @Test
    public void findOne() {
        ClassCourse classCourse = repository.findById("162180701").orElse(null);
        Assert.assertNotNull(classCourse);

    }
    }