package com.school.service;

import com.school.dtoObject.Course;
import com.school.dtoObject.CourseDetail;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.print.Pageable;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDetailServiceTest {

    @Autowired
    private CourseDetailService courseDetailService;

    @Test
    public void save() {
        CourseDetail courseDetail=new CourseDetail();
        courseDetail.setCoursedtlId(KeyUtils.uniqueKey());
        courseDetail.setClassroomId(58);
        courseDetail.setCoursedtlDay(4);
        courseDetail.setCoursedtlSequence(2);
        courseDetail.setCourseId("1548811422423992751");
        CourseDetail result=courseDetailService.save(courseDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
       CourseDetail courseDetail=courseDetailService.findOne("1548823525432796034");
       Assert.assertNotNull(courseDetail);
    }

    @Test
    public void findByClassroomId() {
        PageRequest request=new PageRequest(0,10);
        Page<CourseDetail> courseDetailPage=courseDetailService.findByClassroomId(request,58);
        Assert.assertNotEquals(0,courseDetailPage.getTotalElements());
    }

    @Test
    public void findByCourseId() {
        List<CourseDetail> courseDetailList=courseDetailService.findByCourseId("1548811422423992751");
        Assert.assertNotEquals(0,courseDetailList.size());
    }



}