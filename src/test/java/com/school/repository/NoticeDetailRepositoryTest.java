package com.school.repository;

import com.school.dtoObject.NoticeDetail;
import com.school.utils.KeyUtils;
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
public class NoticeDetailRepositoryTest {

    @Autowired
    private NoticeDetailRepository noticeDetailRepository;

    @Test
    public void create(){
        NoticeDetail noticeDetail = new NoticeDetail();
        noticeDetail.setNdtlId(KeyUtils.uniqueKey());
        noticeDetail.setNtypeId(1);
        noticeDetail.setNdtlTitle("喜报! 我校名誉校长钱七虎院士荣获2018年度国家最高科技奖");
        noticeDetail.setNoticeId(1);
        noticeDetail.setNdtlAuthor("党委宣传部");
        noticeDetail.setNdtlContent("1月8日上午，2018年度国家科学技术奖励大会在北京隆重召开。我校名誉校长钱七虎院士荣获国家最高科学技术奖。在热烈的掌声中，中共中央总书记、国家主席、中央军委主席习近平为国家最高科学技术奖获奖者颁发奖章、证书，并请两位获奖者到主席台就座。<br/>钱七虎，1937年10月26日出生于江苏省昆山市，男，汉族，防护工程和地下工程专家。中共党员。先后毕业于哈尔滨军事工程学院和莫斯科古比雪夫军事工程学院。曾任南京工程兵工程学院院长、总参科技委常委。现任军委科技委顾问、解放军陆军工程大学教授、博士生导师。");
        NoticeDetail result = noticeDetailRepository.save(noticeDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByNdtlTitleContaining() {
        PageRequest request = new PageRequest(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailRepository.findByNdtlTitleContaining("誉校长",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

    @Test
    public void findByNtypeId(){
        PageRequest request = new PageRequest(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailRepository.findByNtypeId(1,request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

    @Test
    public void findByDtlStatus(){
        PageRequest request = new PageRequest(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailRepository.findByNdtlStatus(1,request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }

    @Test
    public void findByNdtlAuthor(){
        PageRequest request = new PageRequest(0,10);
        Page<NoticeDetail> noticeDetailPage = noticeDetailRepository.findByNdtlAuthorContaining("党委",request);
        Assert.assertNotEquals(0,noticeDetailPage.getTotalElements());
    }
}