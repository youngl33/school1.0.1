package com.school.service.impl;

import com.school.dtoObject.Building;
import com.school.service.BuildingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingServiceImplTest {

    @Autowired
    private BuildingService buildingService;

    @Test
    public void create(){
        Building building=new Building();
        building.setBuildingName("二号教学楼");
        Building result=buildingService.save(building);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuildingName(){
        Building building=buildingService.findByBuildingName("二号教学楼");
        Assert.assertNotNull(building);
    }

    @Test
    public void findAll(){
        List<Building>  buildingList=buildingService.findAll();
        Assert.assertNotEquals(0,buildingList.size());
    }

    @Test
    public void findOne()
    {
        Building building=buildingService.findOne(31);
        Assert.assertNotNull(building);
    }

    @Test
    public void findByLocationId(){
        List<Building> result=buildingService.findByLocationId(1);
        Assert.assertNotEquals(0,result.size());
    }
}