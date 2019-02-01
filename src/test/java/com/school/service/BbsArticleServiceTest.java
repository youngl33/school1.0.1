package com.school.service;

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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsArticleServiceTest {

    @Autowired
    private BbsArticleService bbsArticleService;

    @Test
    public void create() {
        BbsArticle bbsArticle = new BbsArticle();
        bbsArticle.setArtId(KeyUtils.uniqueKey());
        bbsArticle.setArtTitle("测试标题");
        bbsArticle.setArtContent("测试内容");
        bbsArticle.setArtUserId("123123");
        BbsArticle result = bbsArticleService.create(bbsArticle);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        BbsArticle result = bbsArticleService.findById("1548758924720457968");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByTitle() {
        PageRequest request = PageRequest.of(0,10);
        Page<BbsArticle> bbsArticlePage = bbsArticleService.findByTitle(request, "测试");
        Assert.assertNotEquals(0,bbsArticlePage.getTotalElements());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,10);
        Page<BbsArticle> bbsArticlePage = bbsArticleService.findAll(request);
        Assert.assertNotEquals(0,bbsArticlePage.getTotalElements());
    }

    @Test
    public void findByTop() {
        PageRequest request = PageRequest.of(0,10);
        Page<BbsArticle> bbsArticlePage = bbsArticleService.findByTop(request,0);
        Assert.assertNotEquals(0,bbsArticlePage.getTotalElements());
    }

    @Test
    public void delete() {
        bbsArticleService.delete("1548834608135300037");
    }

    @Test
    public void addViewer(){
        bbsArticleService.addViewer("1548834705825499107");
    }

    @Test
    public void updateComment(){
        bbsArticleService.updateCommenter("1548834705825499107",+1);
    }

    @Test
    public void updateCommentLess(){
        bbsArticleService.updateCommenter("1548834705825499107",-1);
    }
}