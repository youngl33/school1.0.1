package com.school.repository;

import com.school.dtoObject.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Test
    public void create(){
        Location location=new Location();
        location.setClassroomLocation("111");
        Assert.assertNotNull(location);
    }

    @Test
    public void findByClassroomLocation() {
        Location result=repository.findByClassroomLocation("广兰校区");
        Assert.assertNotNull(result);
    }
}