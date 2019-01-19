package com.school.service;

import com.school.dtoObject.ScheduleDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleDetailServiceTest {

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @Test
    public void findByScheduleId() {
        PageRequest request = new PageRequest(0,30);
        Page<ScheduleDetail> scheduleDetailPage = scheduleDetailService.findByScheduleId(request,"201802");
        Assert.assertNotEquals(0,scheduleDetailPage.getTotalElements());
    }

    @Test
    public void findByYearAndMonthAndDay() {
        ScheduleDetail scheduleDetail = scheduleDetailService.findByYearAndMonthAndDay(2018,12,2);
        Assert.assertNotNull(scheduleDetail);
    }

    @Test
    public void findOne() {
        ScheduleDetail result = scheduleDetailService.findOne("20190411");
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
        scheduleDetailService.delete("201811");
    }

    @Test
    public void save() {
        ScheduleDetail scheduleDetail = new ScheduleDetail();
        scheduleDetail.setScheduleId("201811");
        scheduleDetail.setScheduledtlId("20190412");
        scheduleDetail.setScheduledtlYear(2019);
        scheduleDetail.setScheduledtlMonth(3);
        scheduleDetail.setScheduledtlDay(2);
        scheduleDetail.setScheduledtlWeektoday("星期四");
        scheduleDetail.setScheduledtlWeek(2);
        scheduleDetail.setScheduledtlAttr("工作日");
        ScheduleDetail result = scheduleDetailService.save(scheduleDetail);
    }

    @Test
    public void findByScheduledtlAttrNotNull(){
        List<ScheduleDetail> scheduleDetailList = scheduleDetailService.findByScheduledtlAttrNotIn("");
        Assert.assertNotEquals(0,scheduleDetailList.size());
    }
}