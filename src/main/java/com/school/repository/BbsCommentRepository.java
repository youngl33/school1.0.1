package com.school.repository;

import com.school.dtoObject.BbsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsCommentRepository extends JpaRepository<BbsComment,String> {

    Page<BbsComment> findByArtId(Pageable request, String artId);
}
