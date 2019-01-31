package com.school.service;

import com.school.dtoObject.NoticeDetail;
import com.school.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class NoticeDetailServiceTest {

    @Autowired
    private NoticeDetailService noticeDetailService;

    @Test
    public void findByTitle() {
        PageRequest request = PageRequest.of(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailService.findByNDtlTitle("誉校长","0",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());

    }

    @Test
    public void findByType() {
        PageRequest request = PageRequest.of(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailService.findByType(1,"0",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

    @Test
    public void save() {
        NoticeDetail noticeDetail = new NoticeDetail();
        noticeDetail.setNdtlId(KeyUtils.uniqueKey());
        noticeDetail.setNtypeId(2);
        noticeDetail.setNdtlTitle("2019年元旦贺词");
        noticeDetail.setNoticeId(1);
        noticeDetail.setNdtlAuthor("党委宣传部");
        noticeDetail.setNdtlContent("老师们、同学们、校友们、朋友们：\n" +
                "\n" +
                "        一元复始、万象更新。在这辞旧迎新的美好时刻，我谨代表东华理工大学党委行政向广大师生员工、离退休老同志、校友们、各级领导和社会各界朋友，致以崇高的敬意和新年的祝福！\n" +
                "\n" +
                "回首2018年，在校党委的领导下，我们和衷共济，戮力进取，扎实办好教育、激发科技活力、深化内涵建设，在新征程中迈出了坚实步伐，书写了新时代发展新篇章。\n" +
                "\n" +
                "这一年，我们加强党的领导，科学谋划发展大局。深入学习贯彻习近平新时代中国特色社会主义思想和党的十九大会议精神，以党建工作引领学校全面发展。召开一届五次全体(扩大)会议，客观分析新时代学校发展面临的机遇与挑战，确定了“四方之道”战略思路，提出了学校未来发展的“七大突破、三大提升”主要任务，实现了党建与学校特色高水平大学建设同频共振。\n" +
                "\n" +
                "这一年，我们创新党建工作，做好立德树人大文章。切实担负起党管意识形态的政治责任和主体责任， “补短板”“打基础”“创特色”，获批首批全国党建工作样板支部、全省高校标准化党员活动室，荣获省首届党务技能大赛（党员管理类）二等奖；坚持党管人才，引培并举，人才成效不断显现，师资队伍不断优化；坚持文化育人，弘扬“核军工文化”，实施“六个一”工程，积极推进课堂思政化，加强学生思想政治和人文素质教育，“三下乡”社会实践荣获全国优秀团队、全国千校千项“最具影响力好项目”“百佳创意短视频”等奖项，赴云南怒江团队作为全国社会实践团队唯一代表受邀参加教育部脱贫攻坚现场推进会；教育正能量再获彰显，青年教师周义朋荣获第二届“感动江西十大教育年度人物”，《戈壁红柳核能先锋》获教育部新时代教师风采公益广告全国优胜作品奖。");
        NoticeDetail result = noticeDetailService.save(noticeDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByDtlStatus(){
        PageRequest request = PageRequest.of(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailService.findByNdtlStatus(1,"0",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

    @Test
    public void findByNdtlAuthor(){
        PageRequest request = PageRequest.of(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailService.findByNdtlAuthor("党委","0",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

}