package com.school.repository;


import com.school.dtoObject.Schedule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository repository;

    @Test
    public void create(){
        Schedule schedule = new Schedule();
        schedule.setScheduleId("201811");
        schedule.setScheduleSemester("测试");
        Schedule result = repository.save(schedule);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        Schedule result = repository.findById("201802").orElse(null);
        Assert.assertNotNull(result);

    }

}