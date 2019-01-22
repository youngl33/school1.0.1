package com.school.service.impl;

import com.school.dtoObject.AcademyAdmin;
import com.school.service.AcademyAdminService;
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
public class AcademyAdminServiceImplTest {

    @Autowired
    private AcademyAdminService academyAdminService;

    @Test
    public void create(){

        AcademyAdmin academyAdmin =new AcademyAdmin();
        academyAdmin.setAadmUsername("502989211");
        academyAdmin.setAadmPassword("young123457");
        academyAdmin.setAadmName("洋洋");
        academyAdmin.setAadmAvater("111");
        academyAdmin.setAinfoId("1040502");
        academyAdmin.setAinfoName("外国语学院学院");
        AcademyAdmin result=academyAdminService.save(academyAdmin);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<AcademyAdmin> academyAdminList=academyAdminService.findAll();
        Assert.assertNotEquals(0,academyAdminList.size());

    }

    @Test
    public void findOne(){
        AcademyAdmin academyAdmin=academyAdminService.findOne(1);
        Assert.assertNotNull(academyAdmin);
    }


}