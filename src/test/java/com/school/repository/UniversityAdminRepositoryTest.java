package com.school.repository;

import com.school.dtoObject.Universityadmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityAdminRepositoryTest {

    @Autowired
    private UniversityAdminRepository repository;

    @Test
    public void add(){
        Universityadmin universityadmin = new Universityadmin();
        //universityadmin.setUAdmId(4);
        universityadmin.setUAdmName("管理员");
        universityadmin.setUAdmPassword("123456");
        universityadmin.setUAdmAvater("youngsdf");
        universityadmin.setUAdmUsername("admin2");
        Universityadmin result = repository.save(universityadmin);
        Assert.assertNotNull(result);
    }
}