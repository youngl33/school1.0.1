package com.school.service;

import com.school.dtoObject.Location;
import com.school.repository.LocationRepository;
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
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void create() {
        Location location=new Location();
        location.setClassroomLocation("222");
        locationService.save(location);
        Assert.assertNotNull(location);

    }

    @Test
    public void findOne() {
        Location result=locationService.findOne(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByClassroomLocation() {
        Location result=locationService.findByClassroomLocation("111");
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll(){
        List<Location> locationList= locationService.findAll();
        Assert.assertNotEquals(0,locationList.size());
    }
}