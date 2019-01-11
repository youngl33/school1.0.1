package com.school.repository;

import com.school.dtoObject.UniversityAdmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityAdminRepositoryTest {

    @Autowired
    private UniversityAdminRepository repository;

    @Test
    public void add(){
        UniversityAdmin universityadmin = new UniversityAdmin();
        //universityadmin.setUAdmId(4);
        universityadmin.setUadmName("管理员");
        universityadmin.setUadmPassword("123456");
        universityadmin.setUadmAvater("youngsdf");
        universityadmin.setUadmUsername("admin2");
        UniversityAdmin result = repository.save(universityadmin);
        Assert.assertNotNull(result);
    }
}