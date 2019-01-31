package com.school.repository;

import com.school.dtoObject.Course;
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



@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void create() throws ParseException {
        Course course=new Course();
        course.setCcourseId("162180701");
        course.setTaxId("18020101003");
        course.setClassroomId(53);
        course.setCourseWeek(3);
        course.setCourseDay(1);
        course.setCourseSequence(2);
        course.setCourseDate(DateFormatUtils.dateConverter("2018-09-15"));
        Course result=repository.save(course);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCcourseId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Course> coursePage=repository.findByCcourseId(request,"162180701");
        Assert.assertNotEquals(0,coursePage.getTotalElements());
    }
}