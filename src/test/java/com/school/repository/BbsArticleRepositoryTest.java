package com.school.repository;

import com.school.dtoObject.BbsArticle;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsArticleRepositoryTest {

    @Autowired
    private BbsArticleRepository repository;

    @Test
    public void create(){
        BbsArticle bbsArticle = new BbsArticle();
        bbsArticle.setArtId(KeyUtils.uniqueKey());
        bbsArticle.setArtTitle("测试标题");
        bbsArticle.setArtContent("测试内容");
        bbsArticle.setArtUserId("123123");
        BbsArticle result = repository.save(bbsArticle);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        BbsArticle result = repository.findById("1548758924720457968").orElse(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByTitle(){
        PageRequest request = PageRequest.of(0,10);
        Page<BbsArticle> bbsArticlePage = repository.findByArtTitleContaining(request, "测试");
        Assert.assertNotEquals(0,bbsArticlePage.getTotalElements());
    }

    @Test
    public void findByArtTop(){
        PageRequest request = PageRequest.of(0,10);
        Page<BbsArticle> bbsArticlePage = repository.findByArtTop(request, 0);
        Assert.assertNotEquals(0,bbsArticlePage.getTotalElements());
    }

}