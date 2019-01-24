package com.school.repository;

import com.school.dtoObject.NoticeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDetailRepository extends JpaRepository<NoticeDetail,String> {

    Page<NoticeDetail> findByNdtlTitleContainingAndNoticeBelong(String ndtlTitle,String noticeBelong, Pageable pageable);

    Page<NoticeDetail> findByNtypeIdAndNoticeBelong(Integer ntypeId,String noticeBelong,  Pageable pageable);

    Page<NoticeDetail> findByNdtlStatusAndNoticeBelong(Integer ndtlStatus,String noticeBelong,  Pageable pageable);

    Page<NoticeDetail> findByNdtlAuthorContainingAndNoticeBelong(String ndtlAuthor,String noticeBelong,  Pageable pageable);


}
