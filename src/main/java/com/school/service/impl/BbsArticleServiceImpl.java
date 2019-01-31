package com.school.service.impl;

import com.school.dtoObject.BbsArticle;
import com.school.dtoObject.BbsComment;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.repository.BbsArticleRepository;
import com.school.repository.BbsCommentRepository;
import com.school.service.BbsArticleService;
import com.school.service.BbsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BbsArticleServiceImpl implements BbsArticleService {

    @Autowired
    private BbsArticleRepository bbsArticleRepository;

    @Autowired
    private BbsCommentRepository bbsCommentRepository;

    @Override
    public BbsArticle create(BbsArticle bbsArticle) {
        return bbsArticleRepository.save(bbsArticle);
    }

    @Override
    public BbsArticle findById(String artId) {
        return bbsArticleRepository.findById(artId).orElse(null);
    }

    @Override
    public Page<BbsArticle> findByTitle(Pageable pageable, String title) {
        return bbsArticleRepository.findByArtTitleContaining(pageable,title);
    }

    @Override
    public Page<BbsArticle> findAll(Pageable pageable) {
        return bbsArticleRepository.findAll(pageable);
    }

    @Override
    public Page<BbsArticle> findByTop(Pageable pageable,Integer top) {
        return bbsArticleRepository.findByArtTop(pageable,top);
    }

    @Transactional
    @Override
    public void delete(String id) {
        List<BbsComment> bbsCommentList = bbsCommentRepository.findAll();
        for (BbsComment bbsComment : bbsCommentList) {
            bbsCommentRepository.delete(bbsComment);
        }
        bbsArticleRepository.deleteById(id);
    }

    @Override
    public void addViewer(String id) {
        BbsArticle bbsArticle = findById(id);
        if(bbsArticle==null){
            throw new AdminException(ResultEnum.BBS_ARTICLE_NOT_EXIST);
        }
        bbsArticle.setArtViewer(bbsArticle.getArtViewer()+1);
        create(bbsArticle);
    }

    @Override
    public void updateCommenter(String id, Integer num) {
        BbsArticle bbsArticle = findById(id);
        if(bbsArticle==null){
            throw new AdminException(ResultEnum.BBS_ARTICLE_NOT_EXIST);
        }
        bbsArticle.setArtCommenter(bbsArticle.getArtCommenter()+num);
        create(bbsArticle);
    }
}
