package com.school.service.impl;

import com.school.dtoObject.Classroom;
import com.school.service.ClassroomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassroomServiceImpTest {

    @Autowired
    private ClassroomService classroomService;

    @Test
    public void create(){
        Classroom classroom=new Classroom();
        classroom.setClassroomLocation("广兰校区");
        classroom.setBuildingId(34);
        classroom.setClassroomNo("阶六");
        classroom.setClassroomType("阶梯教室");
        classroom.setClassroomSeats(118);
        Classroom result=classroomService.save(classroom);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByClassroomLocationAndBuildingId(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByClassroomLocationAndBuildingId(request,"枫林校区",32);
        log.info("【查找的数量】：NUmbe={}",classroomPage.getTotalElements());
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findAll(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findAll(request);
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocationAndBuildingNameAndClassroomNo(){
        Classroom classroom=classroomService.findByClassroomLocationAndBuildingIdAndClassroomNo("广兰校区",31,"101");
        Assert.assertNotNull(classroom);
    }

    @Test
    public void findByClassroomLocationAndBuildingNameAndClassroomType(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByClassroomLocationAndBuildingIdAndClassroomType(request,"枫林校区",31,"小教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocation(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByClassroomLocation(request,"枫林校区");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Classroom classroom=classroomService.findOne(88);
        Assert.assertNotNull(classroom);
    }

    @Test
    public void findByClassroomType(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByClassroomType(request,"多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocationAndClassroomType(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByClassroomLocationAndClassroomType(request,"广兰校区","多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByBuildingId(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByBuildingId(request,31);
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByBuildingIdAndClassroomType(){
        PageRequest request=new PageRequest(0,10);
        Page<Classroom> classroomPage=classroomService.findByBuildingIdAndClassroomType(request,31,"多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }


}