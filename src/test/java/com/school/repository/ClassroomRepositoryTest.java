package com.school.repository;

import com.school.dtoObject.Classroom;
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

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClassroomRepositoryTest {
    @Autowired
    private ClassroomRepository repository;

    @Test
    public void create(){
        Classroom classroom=new Classroom();
        classroom.setClassroomLocation("广兰校区");
        classroom.setBuildingId(34);
        classroom.setClassroomNo("阶五");
        classroom.setClassroomType("阶梯教室");
        classroom.setClassroomSeats(118);
        Classroom result=repository.save(classroom);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByClassroomLocationAndBuildingId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByClassroomLocationAndBuildingId(request,"广兰校区",31);
        log.info("【查找的数量】:Number={}",classroomPage.getTotalElements());
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findAll(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findAll(request);
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocationAndBuildingNameAndClassroomNo(){
        Classroom classroom=repository.findByClassroomLocationAndBuildingIdAndClassroomNo("广兰校区",31,"101");
        Assert.assertNotNull(classroom);
    }

    @Test
    public void findByClassroomLocationAndBuildingNameAndClassroomType(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByClassroomLocationAndBuildingIdAndClassroomType(request,"广兰校区",31,"多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocation(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByClassroomLocation(request,"枫林校区");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Classroom classroom= repository.findById(55).orElse(null);
        Assert.assertNotNull(classroom);
    }

    @Test
    public void findByClassroonType(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByClassroomType(request,"多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByClassroomLocationAndClassroomType(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByClassroomLocationAndClassroomType(request,"广兰校区","多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByBuildingId(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByBuildingId(request,31);
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByBuildingIdAndClassroomType(){
        PageRequest request=PageRequest.of(0,10);
        Page<Classroom> classroomPage=repository.findByBuildingIdAndClassroomType(request,31,"多媒体教室");
        Assert.assertNotEquals(0,classroomPage.getTotalElements());
    }

    @Test
    public void findByBuildingId1(){
        List<Classroom> classroomList=repository.findByBuildingId(31);
        Assert.assertNotEquals(0,classroomList.size());
    }

}