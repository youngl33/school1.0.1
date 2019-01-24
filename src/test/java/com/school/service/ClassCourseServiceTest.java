package com.school.service;

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
public class ClassCourseServiceTest {

    @Autowired
    private ClassCourseService classCourseService;

    @Test
    public void create() {
        ClassCourse classcourse=new ClassCourse();
        classcourse.setCcourseId("162180801");
        classcourse.setClassId("1621808");
        classcourse.setScheduleSemester("2018-2019学年第一学期");
        ClassCourse result= classCourseService.save(classcourse);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        ClassCourse classCourse=classCourseService.findOne("162180801");
        Assert.assertNotNull(classCourse);
    }

    @Test
    public void delete() {
        classCourseService.delete("162180801");
    }

    @Test
    public void findByScheduleSemester() {
        PageRequest request=new PageRequest(0,10);
        Page<ClassCourse> classCoursePage=classCourseService.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,classCoursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndClassId() {
        PageRequest request=new PageRequest(0,10);
        Page<ClassCourse> classCoursePage=classCourseService.findByScheduleSemesterAndClassId(request,"2018-2019学年第一学期","1621808");
    }
}