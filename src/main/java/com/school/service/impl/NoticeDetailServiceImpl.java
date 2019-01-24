package com.school.service.impl;

import com.school.dtoObject.NoticeDetail;
import com.school.repository.NoticeDetailRepository;
import com.school.service.NoticeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class NoticeDetailServiceImpl implements NoticeDetailService {

    @Autowired
    private NoticeDetailRepository noticeDetailRepository;

    @Override
    public Page<NoticeDetail> findAll(String noticeBelong,Pageable pageable) {
        return noticeDetailRepository.findAll(pageable);
    }

    @Override
    public Page<NoticeDetail> findByType(Integer ntypeId,String noticeBelong,Pageable pageable) {
        return noticeDetailRepository.findByNtypeIdAndNoticeBelong(ntypeId,noticeBelong,pageable);
    }

    @Override
    public NoticeDetail save(NoticeDetail noticeDetail) {
        return noticeDetailRepository.save(noticeDetail);
    }

    @Override
    public Page<NoticeDetail> findByNDtlTitle(String noticeBelong,String ndtlName,Pageable pageable) {
        return noticeDetailRepository.findByNdtlTitleContainingAndNoticeBelong(ndtlName,noticeBelong,pageable);
    }

    @Override
    public Page<NoticeDetail> findByNdtlStatus(Integer ndtlStatus,String noticeBelong, Pageable pageable) {
        return noticeDetailRepository.findByNdtlStatusAndNoticeBelong(ndtlStatus,noticeBelong,pageable);
    }

    @Override
    public NoticeDetail findOne(String ndtlId) {
        return noticeDetailRepository.findById(ndtlId).orElse(null);
    }

    @Override
    public Page<NoticeDetail> findByNdtlAuthor(String noticeBelong,String ndtlAuthor, Pageable pageable) {
        return noticeDetailRepository.findByNdtlAuthorContainingAndNoticeBelong(ndtlAuthor,noticeBelong,pageable);
    }
}
