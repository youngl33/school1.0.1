package com.school.repository;

import com.school.dtoObject.NoticeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeTypeRepositoryTest {

    @Autowired
    private NoticeTypeRepository repository;

    @Test
    public void create(){
        NoticeType noticeType = new NoticeType();
        noticeType.setNtypeName("通知");
        NoticeType result = repository.save(noticeType);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        NoticeType result = repository.findById(1).orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByNtypeName(){
        NoticeType result = repository.findByNtypeName("通知");
        Assert.assertNotNull(result);
    }

}