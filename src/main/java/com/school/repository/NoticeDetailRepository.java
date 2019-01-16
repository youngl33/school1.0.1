package com.school.repository;

import com.school.dtoObject.NoticeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDetailRepository extends JpaRepository<NoticeDetail,String> {

    Page<NoticeDetail> findByNdtlTitleContaining(String ndtlTitle, Pageable pageable);

    Page<NoticeDetail> findByNtypeId(Integer ntypeId, Pageable pageable);

    Page<NoticeDetail> findByNdtlStatus(Integer ndtlStatus, Pageable pageable);


}
