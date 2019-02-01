package com.school.repository;

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

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void create(){
        Course course=new Course();
        course.setCourseId(KeyUtils.uniqueKey());
        course.setSubjectId("1040501001");
        course.setTeacherId("200709002");
        course.setCourseTime(40);
        course.setCourseScore(3.5);
        course.setCourseNum(130);
        course.setCourseTotalnum(150);
        course.setCourseBegin(3);
        course.setCourseEnd(15);
        course.setScheduleSemester("2018-2019学年第一学期");
        Course result=repository.save(course);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByScheduleSemester(){
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=repository.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,coursePage.getTotalElements());

    }

    @Test
    public void findByTeacherId(){
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=repository.findByTeacherId(request,"200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());

    }

    @Test
    public void findByScheduleSemesterAndTeacherId(){
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=repository.findByScheduleSemesterAndTeacherId(request,"2018-2019学年第一学期","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void find1(){
        List<Course> courseList=repository.findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(3,5);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void find2(){
        List<Course> courseList=repository.findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(5,15);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void find3(){
        List<Course> courseList=repository.findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(4,5);
        Assert.assertNotEquals(0,courseList.size());
    }

    @Test
    public void findBySubjectId(){
        PageRequest request=new PageRequest(0,10);
        Page<Course> coursePage=repository.findBySubjectId(request,"1040501001");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=repository.findByScheduleSemesterAndSubjectId(request,"2018-2019学年第一学期","1040501001");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findBySubjectIdAndTeacherId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=repository.findBySubjectIdAndTeacherId(request,"1040501001","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectIdAndTeacherId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=repository.findByScheduleSemesterAndSubjectIdAndTeacherId(request,"2018-2019学年第一学期","1040501001","200709002");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }

    @Test
    public void findOne(){
        Course result = repository.findById("1549002715891559975").orElse(null);
        Assert.assertNotNull(result);
    }
}