package com.school.repository;

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

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDetailRepositoryTest {

    @Autowired
    private CourseDetailRepository repository;

    @Test
    public void create(){
        CourseDetail courseDetail=new CourseDetail();
        courseDetail.setCoursedtlId(KeyUtils.uniqueKey());
        courseDetail.setClassroomId(55);
        courseDetail.setCoursedtlDay(1);
        courseDetail.setCoursedtlSequence(1);
        courseDetail.setCourseId("1548811422423992751");
        CourseDetail result=repository.save(courseDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByClassroomId() {
        PageRequest request=new PageRequest(0,10);
        Page<CourseDetail> courseDetailPage=repository.findByClassroomId(request,55);
        Assert.assertNotEquals(0,courseDetailPage.getTotalElements());
    }

    @Test
    public void findByCourseId() {
        List<CourseDetail> courseDetailList=repository.findByCourseId("1548811422423992751");
        Assert.assertNotEquals(0,courseDetailList.size());

    }



}