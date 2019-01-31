package com.school.service;

import com.school.dtoObject.UniversityAdmin;
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
public class UniversityAdminSeviceTest {

    @Autowired
    private UniversityAdminSevice universityAdminSevice;

    @Test
    public void create() {
        UniversityAdmin universityadmin = new UniversityAdmin();
        universityadmin.setUadmName("管理员");
        universityadmin.setUadmPassword("123456");
        universityadmin.setUadmAvater("youngsdf");
        universityadmin.setUadmUsername("uadmin");
        UniversityAdmin result = universityAdminSevice.create(universityadmin);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        UniversityAdmin result = universityAdminSevice.findOne(2);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByUserName() {
        UniversityAdmin result = universityAdminSevice.findByUserName("admin2");
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<UniversityAdmin> universityAdminList = universityAdminSevice.findAll();
        Assert.assertNotEquals(0,universityAdminList.size());
    }
}