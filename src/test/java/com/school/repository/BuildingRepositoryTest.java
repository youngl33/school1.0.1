package com.school.repository;

import com.school.dtoObject.Building;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest

public class BuildingRepositoryTest {

    @Autowired
    private BuildingRepository repository;

    @Test
    public void create(){
        Building building=new Building();
        building.setBuildingName("国防科技楼");
        Building result=repository.save(building);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuildingName(){
        Building building=repository.findByBuildingName("三号教学楼");
        Assert.assertNotNull(building);
    }

    @Test
    public void find(){
        Building building=repository.findById(31).orElse(null);
        Assert.assertNotNull(building);
    }

    @Test
    public void findAll(){
        List<Building> buildingList=repository.findAll();
        Assert.assertNotEquals(0,buildingList.size());
    }
}