package com.school.service;

import com.school.dtoObject.Schedule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void findAll() {
        List<Schedule> schedules = scheduleService.findAll();
        Assert.assertNotEquals(0,schedules.size());
    }

    @Test
    public void findOne() {
        Schedule schedule = scheduleService.findOne("201802");
        Assert.assertNotNull(schedule);
    }

    @Test
    public void create(){
        Schedule schedule = new Schedule();
        schedule.setScheduleId("201811");
        schedule.setScheduleSemester("测试");
        Schedule result = scheduleService.create(schedule);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete(){
        scheduleService.deleteById("201811");
    }
}