package com.school.service;

import com.school.dtoObject.NoticeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeDetailService {

    /** 通过ID查找文章   */
    NoticeDetail findOne(String ndtlId);

    /** 查找所有文章       */
    Page<NoticeDetail> findAll(String noticeBelong,Pageable pageable);

    /** 通过标题模糊查找     */
    Page<NoticeDetail> findByNDtlTitle(String ndtlName,String noticeBelong,Pageable pageable);

    /** 通过类别查找       */
    Page<NoticeDetail> findByType(Integer ntypeId,String noticeBelong,Pageable pageable);

    /** 保存文章信息       */
    NoticeDetail save(NoticeDetail noticeDetail);

    /** 通过状态查找文章     */
    Page<NoticeDetail> findByNdtlStatus(Integer ndtlStatus,String noticeBelong,Pageable pageable);

    /** 通过作者查找文字  */
    Page<NoticeDetail> findByNdtlAuthor(String ndtlAuthor,String noticeBelong,Pageable pageable);
}
