package com.school.repository;

import com.school.dtoObject.AcademyInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyInfoRepositoryTest {

    @Autowired
    private AcademyInfoRepository repository;

    @Test
    public void create(){
        AcademyInfo academyInfo = new AcademyInfo();
        academyInfo.setAinfoId("1040501");
        academyInfo.setAinfoName("外国语学院");
        academyInfo.setAinfoDescription("东华理工大学外国语学院成立于2005年，其前身为外语教研室，隶属于学校基础部，主要承担全校学生的大学外语教学。1999年成立外语系，开始招收本科生。2005年6月，在原外语系的基础上，正式组建成立外国语学院。");
        AcademyInfo result = repository.save(academyInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        AcademyInfo academyInfo = repository.findById("1040501").orElse(null);
        Assert.assertNotNull(academyInfo);
    }

    @Test
    public void update(){
        AcademyInfo academyInfo = repository.findById("1040501").orElse(null);

        repository.delete(academyInfo);
    }
    @Test
    public void findByAInfoName(){
        AcademyInfo academyInfo = repository.findByAinfoName("软件学院");
        Assert.assertNotNull(academyInfo);
    }
}