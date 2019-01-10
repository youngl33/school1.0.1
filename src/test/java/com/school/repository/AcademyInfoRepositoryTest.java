package com.school.repository;

import com.school.dtoObject.Academyinfo;
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
        Academyinfo academyinfo = new Academyinfo();
        academyinfo.setAInfoId("1040501");
        academyinfo.setAInfoName("软件学院");
        academyinfo.setAInfoDescription("东华理工大学软件学院是为适应我国经济结构战略性调整以及软件产业发展对人才的迫切需要，实现我国软件人才培养的跨越式发展，于2002年创办的江西省第一批软件学院之一，在第二批本科批次招生，毕业生颁发东华理工大学毕业证书及学位证书，2018年在校本科生2400余人。 一直以来学院探索多途径合作办学的管理体制与运行机制，积极发展与国内外高校及知名企业的交流与合作，不断实现办学专业化。学院以坚持创新创业、面向需求、质量第一为根本宗旨，并实行“专业教育学分制、 素质教育学苑式、 产学研一体化”的办学模式。");
        Academyinfo result = repository.save(academyinfo);
        Assert.assertNotNull(result);
    }
}