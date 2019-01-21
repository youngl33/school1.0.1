package com.school.repository;

import com.school.dtoObject.Class;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassRepositoryTest {

    @Autowired
    private ClassRepository repository;

    @Test
    public void create(){
        Class cc= new Class();
        cc.setClassId("1621807");
        cc.setMajorId("080902");
        cc.setMajorName("软件工程");
        cc.setClassNum(36);
        cc.setClassGrade(2016);
        cc.setAinfoName("软件学院");
        cc.setTeacherName("李丽");
        Class result = repository.save(cc);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        Class result = repository.findById("1621807").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByMajorId(){
        List<Class> result = repository.findByMajorId("080902");
        Assert.assertNotEquals(0,result.size());
    }
}