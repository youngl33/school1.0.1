package com.school.repository;

import com.school.dtoObject.BbsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BbsArticleRepository extends JpaRepository<BbsArticle,String> {

    Page<BbsArticle> findByArtTitleContaining(Pageable pageable,String title);

    Page<BbsArticle> findByArtTop(Pageable pageable,Integer artTop);
}
