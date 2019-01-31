package com.school.service.impl;

import com.school.dtoObject.BbsComment;
import com.school.repository.BbsCommentRepository;
import com.school.service.BbsArticleService;
import com.school.service.BbsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BbsCommentServiceImpl implements BbsCommentService {

    @Autowired
    private BbsArticleService bbsArticleService;

    @Autowired
    private BbsCommentRepository bbsCommentRepository;

    @Transactional
    @Override
    public BbsComment create(BbsComment bbsComment) {
        bbsArticleService.updateCommenter(bbsComment.getArtId(),1);
        return bbsCommentRepository.save(bbsComment);
    }

    @Override
    public Page<BbsComment> findAll(Pageable pageable) {
        return bbsCommentRepository.findAll(pageable);
    }

    @Override
    public BbsComment findById(String id) {
        return bbsCommentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<BbsComment> findByArtId(PageRequest request, String artId) {
        return bbsCommentRepository.findByArtId(request,artId);
    }

    @Transactional
    @Override
    public void delete(String id) {
        BbsComment bbsComment = findById(id);
        bbsArticleService.updateCommenter(bbsComment.getArtId(),-1);
        bbsCommentRepository.deleteById(id);
    }
}
