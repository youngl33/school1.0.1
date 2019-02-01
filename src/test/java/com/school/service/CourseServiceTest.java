package com.school.service;

import com.school.dtoObject.Course;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Parameter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void create() {
        Course course=new Course();
        course.setCourseId(KeyUtils.uniqueKey());
        course.setSubjectId("1040502001");
        course.setTeacherId("200902001");
        course.setCourseTime(40);
        course.setCourseScore(4.0);
        course.setCourseNum(89);
        course.setCourseTotalnum(100);
        course.setCourseBegin(3);
        course.setCourseEnd(15);
        course.setScheduleSemester("2018-2019学年第一学期");
        Course result=courseService.save(course);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        Course course=courseService.findOne("1549002199247310478");
        Assert.assertNotNull(course);
    }

    @Test
    public void findByScheduleSemester() {
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=courseService.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByTeacherId() {
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=courseService.findByTeacherId(request,"200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndTeacherId() {
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=courseService.findByScheduleSemesterAndTeacherId(request,"2018-2019学年第一学期","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void find1(){
        List<Course> courseList=courseService.findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(2,3);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void find2(){
        List<Course> courseList=courseService.findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(4,15);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void find3(){
        List<Course> courseList=courseService.findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(3,6);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void findBySubjectId(){
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=courseService.findBySubjectId(request,"1040501001");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=courseService.findByScheduleSemesterAndSubjectId(request,"2018-2019学年第一学期","1040501001");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findBySubjectIdAndTeacherId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=courseService.findBySubjectIdAndTeacherId(request,"1040501001","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectIdAndTeacherId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=courseService.findByScheduleSemesterAndSubjectIdAndTeacherId(request,"2018-2019学年第一学期","1040501001","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findAll(){
        PageRequest request= PageRequest.of(0,10);
        Page<Course> coursePage=courseService.findAll(request);
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }
}