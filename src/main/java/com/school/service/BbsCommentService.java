package com.school.service;

import com.school.dtoObject.BbsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface BbsCommentService {

    /**
     * 添加一条评论
     */
    BbsComment create(BbsComment bbsComment);

    /**
     * 查找所有评论
     */
    Page<BbsComment> findAll(Pageable pageable);

    /**
     * 通过id查找一条评论
     */
    BbsComment findById(String id);

    /**
     * 通过文章id查找评论
     */
    Page<BbsComment> findByArtId(PageRequest request,String artId);

    /**
     * 删除一条评论
     */
    void delete(String id);
}
