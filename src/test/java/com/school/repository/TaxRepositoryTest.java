package com.school.repository;

import com.school.dtoObject.Tax;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxRepositoryTest {

    @Autowired
    private  TaxRepository repository;
 /*   @Test
    public void create(){
        Tax tax=new Tax();
        tax.setTaxId("180201002");
        tax.setSubjectId("1040501002");
        tax.setSubjectName("操作系统原理");
        tax.setTeacherName("张军");
        tax.setTeacherId("201002002");
        tax.setAvailableNum(180);
        tax.setScheduleSemester("2018-2019学年第一学期");
        tax.setTaxScore(3.5);
        tax.setTaxTime(40);
        tax.setTaxWeek("3-12周");
        Tax result=repository.save(tax);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByScheduleSemester(){
        PageRequest request=new PageRequest(0,10);
        Page<Tax> taxPage=repository.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndTeacherName(){
        PageRequest request=new PageRequest(0,10);
        Page<Tax> taxPage=repository.findByScheduleSemesterAndTeacherName(request,"2018-2019学年第一学期","夏桂兰");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectName(){
        PageRequest request=new PageRequest(0,10);
        Page<Tax> taxPage=repository.findByScheduleSemesterAndSubjectNameContaining(request,"2018-2019学年第一学期","计算机组成原理");
    }

    @Test
    public void findByScheduleSemesterAndSubjectNameAndTeacherName(){
        PageRequest request=new PageRequest(0,10);
        Page<Tax> taxPage=repository.findByScheduleSemesterAndSubjectNameContainingAndTeacherName(request,"2018-2019学年第一学期","大学英语I","夏桂兰");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }
*/

}