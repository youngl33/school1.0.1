package com.school.service;

import com.school.dtoObject.NoticeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeTypeServiceTest {

    @Autowired
    private NoticeTypeService noticeTypeService;

    @Test
    public void findByNtypeName() {
        NoticeType result = noticeTypeService.findByNtypeName("通知");
        Assert.assertNotNull(result);
    }

    @Test
    public void save() {
        NoticeType noticeType = new NoticeType();
        noticeType.setNtypeName("学校新闻");
        NoticeType result = noticeTypeService.save(noticeType);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByNtypeId() {
        NoticeType result = noticeTypeService.findByNtypeId(2);
        Assert.assertNotNull(result);
    }
}