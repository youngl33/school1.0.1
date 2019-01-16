package com.school.service;

import com.school.dtoObject.NoticeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeDetailService {

    /** 通过ID查找文章   */
    public NoticeDetail findOne(String ndtlId);

    /** 查找所有文章       */
    public Page<NoticeDetail> findAll(Pageable pageable);

    /** 通过标题模糊查找     */
    public Page<NoticeDetail> findByNDtlTitle(String ndtlName,Pageable pageable);

    /** 通过类别查找       */
    public Page<NoticeDetail> findByType(Integer ntypeId,Pageable pageable);

    /** 保存文章信息       */
    public NoticeDetail save(NoticeDetail noticeDetail);

    /** 通过状态查找文章     */
    public Page<NoticeDetail> findByNdtlStatus(Integer ndtlStatus,Pageable pageable);


}
