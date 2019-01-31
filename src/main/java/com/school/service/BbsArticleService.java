package com.school.service;

import com.school.dtoObject.BbsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BbsArticleService {

    /**
     * 添加一篇文章
     */
    BbsArticle create(BbsArticle bbsArticle);

    /**
     * 通过id查找一篇文章
     */
    BbsArticle findById(String artId);

    /**
     * 通过标题模糊搜索文章
     */
    Page<BbsArticle> findByTitle(Pageable pageable, String title);

    /**
     * 查找所有文章
     */
    Page<BbsArticle> findAll(Pageable pageable);

    /**
     * 查找置顶文章
     */
    Page<BbsArticle> findByTop(Pageable pageable, Integer top);

    /**
     * 通过id删除一篇文章
     */
    void delete(String id);

    /**
     * 访问量加1
     */
    void addViewer(String id);

    /**
     * 修改评论数
     * @param num 修改的数量,num可为正负
     */
    void updateCommenter(String id,Integer num);
}
