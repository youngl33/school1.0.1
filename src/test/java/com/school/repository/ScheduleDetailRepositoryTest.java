package com.school.repository;


import com.school.dtoObject.ScheduleDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleDetailRepositoryTest {


    @Autowired
    private ScheduleDetailRepository repository;

    @Test
    public void create(){
        ScheduleDetail scheduleDetail = new ScheduleDetail();
        scheduleDetail.setScheduleId("201802");
        scheduleDetail.setScheduledtlId("20190311");
        scheduleDetail.setScheduledtlYear(2019);
        scheduleDetail.setScheduledtlMonth(3);
        scheduleDetail.setScheduledtlDay(2);
        scheduleDetail.setScheduledtlWeektoday("星期四");
        scheduleDetail.setScheduledtlWeek(2);
        scheduleDetail.setScheduledtlAttr("工作日");
        ScheduleDetail result = repository.save(scheduleDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        ScheduleDetail result = repository.findById("20190311").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByymd(){
        ScheduleDetail scheduleDetail = repository.findByScheduledtlYearAndScheduledtlMonthAndScheduledtlDay(2018,12,2);
        Assert.assertNotNull(scheduleDetail);
    }

    @Test
    public void findByMonth(){
        List<ScheduleDetail> scheduleDetailList = repository.findByScheduledtlYearAndScheduledtlMonth(2018,10) ;
        Assert.assertNotEquals(0,scheduleDetailList.size());

    }

    @Test
    public void findByScheduleId(){
        PageRequest request = PageRequest.of(0,30);
        Page<ScheduleDetail> scheduleDetailPage = repository.findByScheduleId(request,"201802");
        Assert.assertNotEquals(0,scheduleDetailPage.getTotalElements());
    }


    @Test
    public void findByScheduledtlAttrIn(){
        List<ScheduleDetail> scheduleDetailList = repository.findByScheduledtlAttrNotIn("");
        Assert.assertNotEquals(0,scheduleDetailList.size());
    }

    @Test
    public void deleteById(){
        repository.deleteById("20190311");
    }
}