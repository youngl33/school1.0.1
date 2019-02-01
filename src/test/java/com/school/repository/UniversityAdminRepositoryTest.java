package com.school.repository;

import com.school.dtoObject.UniversityAdmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityAdminRepositoryTest {

    @Autowired
    private UniversityAdminRepository repository;

    @Test
    public void add(){
        UniversityAdmin universityadmin = new UniversityAdmin();
        universityadmin.setUadmName("超级管理员");
        universityadmin.setUadmPassword("123456");
        universityadmin.setUadmAvater("youngsdf");
        universityadmin.setUadmUsername("admin");
        UniversityAdmin result = repository.save(universityadmin);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByUadmName(){
        UniversityAdmin universityAdmin = repository.findByUadmUsername("admin");
        Assert.assertNotNull(universityAdmin);
    }

    @Test
    public void findAll(){
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<UniversityAdmin> universityAdminPage = repository.findAll(pageRequest);
        Assert.assertNotEquals(0,universityAdminPage.getTotalElements());
    }
}