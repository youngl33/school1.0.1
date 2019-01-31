package com.school.service;

import com.school.dtoObject.BbsComment;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsCommentServiceTest {

    @Autowired
    private BbsCommentService bbsCommentService;

    @Test
    public void create() {
        BbsComment bbsComment = new BbsComment();
        bbsComment.setComId(KeyUtils.uniqueKey());
        bbsComment.setArtId("1548758924720457968");
        bbsComment.setComContent("test");
        bbsComment.setComUserId("12313");
        BbsComment result = bbsCommentService.create(bbsComment);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,10);
        Page<BbsComment> bbsCommentPage = bbsCommentService.findAll(request);
        Assert.assertNotEquals(0,bbsCommentPage.getTotalElements());
    }

    @Test
    public void findById() {
        BbsComment result = bbsCommentService.findById("1548768605779185235");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByArtId() {
        PageRequest request = PageRequest.of(0,10);
        Page<BbsComment> bbsCommentPage = bbsCommentService.findByArtId(request,"1548758924720457968");
        Assert.assertNotEquals(0,bbsCommentPage.getTotalElements());
    }

    @Test
    public void delete() {
        bbsCommentService.delete("1548835111830648804");
    }
}