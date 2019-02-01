/*
package com.school.service;

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
public class TaxServiceTest {

    @Autowired
    private TaxService taxService;

    @Test
    public void create() {
        Tax tax = new Tax();
        tax.setTaxId("18020102002");
        tax.setSubjectId("1040501002");
        tax.setSubjectName("操作系统原理");
        tax.setTeacherName("余若盈");
        tax.setTeacherId("201002001");
        tax.setAvailableNum(180);
        tax.setScheduleSemester("2018-2019学年第一学期");
        tax.setTaxScore(3.5);
        tax.setTaxTime(40);
        tax.setTaxWeek("3-12周");
        Tax result = taxService.save(tax);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByScheduleSemester() {
        PageRequest request=PageRequest.of(0,10);
        Page<Tax> taxPage=taxService.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectName() {
        PageRequest request=PageRequest.of(0,10);
        Page<Tax> taxPage=taxService.findByScheduleSemesterAndSubjectNameContaining(request,"2018-2019学年第一学期","操作系统");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndTeacherName() {
        PageRequest request=PageRequest.of(0,10);
        Page<Tax> taxPage=taxService.findByScheduleSemesterAndTeacherName(request,"2018-2019学年第一学期","余若盈");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndSubjectNameContainingAndTeacherName() {
        PageRequest request=PageRequest.of(0,10);
        Page<Tax> taxPage=taxService.findByScheduleSemesterAndSubjectNameContainingAndTeacherName(request,"2018-2019学年第一学期","操作","余若盈");
        Assert.assertNotEquals(0,taxPage.getTotalElements());
    }

    @Test
    public void findOne(){
        Tax tax=taxService.findOne("18020101002");
        Assert.assertNotNull(tax);
    }

    @Test
    public void delete(){
        taxService.delete("18010101001");
    }
}*/
