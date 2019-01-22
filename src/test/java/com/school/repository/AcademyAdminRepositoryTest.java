package com.school.repository;

import com.school.dtoObject.AcademyAdmin;
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

public class AcademyAdminRepositoryTest {

    @Autowired
    private AcademyAdminRepository repository;

    @Test
    public void create(){
        AcademyAdmin academyAdmin =new AcademyAdmin();
        academyAdmin.setAadmUsername("928219339");
        academyAdmin.setAadmPassword("yrylsm1215");
        academyAdmin.setAadmName("Sapianduo");
        academyAdmin.setAadmAvater("111");
        academyAdmin.setAinfoId("1040501");
        academyAdmin.setAinfoName("软件学院");
        AcademyAdmin result=repository.save(academyAdmin);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll(){
        List<AcademyAdmin> academyAdminList=repository.findAll();
        Assert.assertNotEquals(0,academyAdminList.size());
    }

    @Test
    public void findOne(){
        AcademyAdmin academyAdmin=repository.findById(1).orElse(null);
        Assert.assertNotNull(academyAdmin);
    }
}