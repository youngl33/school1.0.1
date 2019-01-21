package com.school.repository;

import com.school.dtoObject.Major;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MajorRepositioryTest {

    @Autowired
    private MajorRepositiory repositiory;

    @Test
    public void create(){
        Major major = new Major();
        major.setMajorId("080902");
        major.setAinfoId("1040501");
        major.setMajorName("软件工程");
        major.setAinfoName("软件学院");
        major.setMajorDescription("东华理工大学软件学院成立于2002年，是江西省第一批软件学院之一，同年，软件工程专业开始招生。长期以来，学院探索了多种途径的合作办学，积极发展与国内外高校及知名企业的交流与合作，不断实现办学体制与管理机制的创新，坚持以面向需求、创新创业、质量第一为根本宗旨，并实行“专业教育学分制、素质教育学苑式、产学研一体化”的办学模式。目前，东华理工大学软件工程专业已成为国家级“卓越工程师培养计划”专业。软件工程专业以培养德智体等全面发展，具有良好的交流与组织协调能力、竞争能力和外语交流能力，获得工程师或科学研究基本训练、具有创新精神和较强实践能力，掌握扎实的软件工程基础理论知识，能从事高质量应用软件产品分析、设计、开发、测试以及编制软件工程项目文档等工作的应用型人才为目标，注重培养学生的获取知识的能力、应用知识能力、创新能力以及各项专业能力。本专业现拥有高级职称教师5人，博士（含博士在读）教师 8 人。专业教师主持省级以上科研教研项目30多项，发表核心以上论文40余篇。\n<br>" +
                "\n<br>" +
                "经过多年的探索和实践，形成了较鲜明的办学特色，取得了一系列教学成果。\n<br>" +
                "\n<br>" +
                "1、建立了以企业需求为导向的人才培养体系\n<br>" +
                "\n<br>" +
                "在人才培养的过程中，始终以企业需求为导向，以能力培养为核心，形成了一个以企业需求为输入、以适应企业需求的软件人才为输出的应用型软件人才培养体系。\n<br>" +
                "\n<br>" +
                "2、形成了以能力培养为核心的实践教学模式\n<br>" +
                "\n<br>" +
                "在实践教学中，贯彻工程教育理念，建立了课程实验、课程设计、项目实训、企业实习四位一体的多层次实践教学模式。\n<br>" +
                "\n<br>" +
                "3、以校企合作为途径，促进校企互动、产学互动\n<br>" +
                "\n<br>" +
                "积极利用企业技术资源开展实践教学，将企业引入到人才培养过程中来，保证人才培养紧跟市场发展，满足市场需求。与多家知名企业进行合作，建立了20多个学生校外实习实践基地。\n<br>" +
                "\n<br>" +
                "4、大力开展国际化人才培养\n<br>" +
                "\n<br>" +
                "软件专业长期聘请多名外教参与课程教学，培养计划中有5-6门课程长期由外教主讲，大大提升了学生的实际外语应用能力。");
        Major result = repositiory.save(major);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        Major result = repositiory.findById("080902").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByAinfoId(){
        List<Major> majorList = repositiory.findByAinfoId("1040501");
        Assert.assertNotEquals(0,majorList.size());
    }

    @Test
    public void findMajorName(){
        Major result = repositiory.findByMajorName("软件工程");
        Assert.assertNotNull(result);
    }
}