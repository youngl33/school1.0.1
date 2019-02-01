package com.school.repository;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseResourceRepositoryTest {

    @Autowired
    private CourseResourceRepository resourceRepository;

    @Test
    public void create(){
        CourseResource courseResource = new CourseResource();
        courseResource.setResId(KeyUtils.uniqueKey());
        courseResource.setCourseId("123123");
        courseResource.setResAttribute(1);
        courseResource.setResAddr("123123");
        CourseResource result = resourceRepository.save(courseResource);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        CourseResource result = resourceRepository.findById("1548987813821701029").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCourseId(){
        PageRequest request = PageRequest.of(0,10);
        Page<CourseResource> courseResourcePage = resourceRepository.findByCourseId(request, "123123");
        Assert.assertNotEquals(0,courseResourcePage.getTotalElements());
    }
}