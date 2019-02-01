package com.school.service;

import com.school.dtoObject.CourseResource;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseResourceServiceTest {

    @Autowired
    private CourseResourceService courseResourceService;

    @Test
    public void create() {
        CourseResource courseResource = new CourseResource();
        courseResource.setResId(KeyUtils.uniqueKey());
        courseResource.setCourseId("123123");
        courseResource.setResAttribute(1);
        courseResource.setResName("测试");
        courseResource.setResAddr("123123");
        CourseResource result = courseResourceService.create(courseResource);
        Assert.assertNotNull(result);

    }

    @Test
    public void findById() {
        CourseResource result = courseResourceService.findById("1548987813821701029");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCourseId() {
        PageRequest request = PageRequest.of(0,10);
        Page<CourseResource> courseResourcePage = courseResourceService.findByCourseId(request, "123123");
        Assert.assertNotEquals(0,courseResourcePage.getTotalElements());
    }
}