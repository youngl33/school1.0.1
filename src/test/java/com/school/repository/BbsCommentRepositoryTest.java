package com.school.repository;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsCommentRepositoryTest {

    @Autowired
    private BbsCommentRepository bbsCommentRepository;

    @Test
    public void create(){
        BbsComment bbsComment = new BbsComment();
        bbsComment.setComId(KeyUtils.uniqueKey());
        bbsComment.setArtId("1548758924720457968");
        bbsComment.setComContent("test");
        bbsComment.setComUserId("12313");
        BbsComment result = bbsCommentRepository.save(bbsComment);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByArtId(){
        PageRequest request = PageRequest.of(0,10);
        Page<BbsComment> result = bbsCommentRepository.findByArtId(request,"1548758924720457968");
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}