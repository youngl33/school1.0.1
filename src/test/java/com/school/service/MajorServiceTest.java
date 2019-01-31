package com.school.service;

import com.school.dtoObject.Major;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MajorServiceTest {

    @Autowired
    private MajorService majorService;

    @Test
    public void create() {
        Major major = new Major();
        major.setMajorId("080903");
        major.setAinfoId("1040501");
        major.setMajorName("网络工程");
        major.setAinfoName("软件学院");
        major.setMajorDescription("主干课程：现代通信技术、计算机通信与网络、计算密码学、物联网技术概论、无线组网技术、无线传感器网络、Linux/Unix操作系统、TCP/IP协议、高级路由与交换技术、系统集成与综合布线、服务器配置与管理、网络分析与优化、计算机网络安全、计算机病毒与防治、防火墙原理与实用技术、接入网技术、信息网络对抗技术、网络工程项目管理、计算机组成原理、操作系统原理、C语言程序设计、数据结构与算法设计、面向对象程序设计、关系数据库原理与应用、WEB程序设计、JAVA程序设计、网络基础应用实习、网络建设与管理实践、校企合作工程应用项目实训、企业顶岗实习、毕业实习、毕业设计等。\n<br>" +
                "\n<br>" +
                "就业方向：毕业生适宜在以高新信息技术为主的网络产品生产企业、科研院所、各类学校、行政事业机关等从事网络工程项目开发和管理，网络信息系统设计与开发，网络建设项目维护与管理、网络应用教学与教育培训等工作，也为在IT领域中自主创业提供了系统知识与专业技术支持。");
        Major result = majorService.create(major);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        Major result = majorService.findById("080903");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByMajorName() {
        Major result = majorService.findByMajorName("电子商务");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByAinfoId() {
        List<Major> majors = majorService.findByAinfoId("1040501");
        Assert.assertNotEquals(0,majors.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,10);
        Page<Major> majors = majorService.findAll(request);
        Assert.assertNotEquals(0,majors.getTotalElements());
    }
}