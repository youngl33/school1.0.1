package com.school.service.impl;

import com.school.dtoObject.AcademyInfo;
import com.school.service.AcademyInfoService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AcademyInfoDTOServiceImplTest {

    @Autowired
    private AcademyInfoService academyInfoService;

    @Test
    public void findOne() {
        AcademyInfo academyInfo = academyInfoService.findOne("1040502");
        log.info("【查询单个订单】,result={}",academyInfo);
        Assert.assertNotNull(academyInfo);
    }

    @Test
    public void save() {
        AcademyInfo academyInfo = new AcademyInfo();
        academyInfo.setAinfoId("1040503");
        academyInfo.setAinfoName("东华理工大学核科学与工程学院");
        academyInfo.setAinfoDescription("2001年，东华理工大学在核技术及应用学科建设的基础上，为适应国防核事业和核工业发展的需要，开设了核工程与核技术专业。\n" +
                "2005年，学校实行校院两级管理，组合核科学与技术、地球物理学、物理学和测控科学与仪器四大学科成立了核工程技术学院。\n" +
                "2012年，核学院改名核工程与地球物理学院。\n" +
                "2016年，学校进一步推进人才培养综合改革，重新组建了核科学与工程学院。");
        AcademyInfo result = academyInfoService.save(academyInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByAinfoName() {
        AcademyInfo result = academyInfoService.findByAinfoName("东华理工大学核科学与工程学院");
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<AcademyInfo> academyInfoList = academyInfoService.findAll();
        log.info("【查找的数量】,Number={}",academyInfoList.size());
        Assert.assertNotEquals(0,academyInfoList.size());

    }
}