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
    public Page<NoticeDetail> findAll(Pageable pageable) {
        return noticeDetailRepository.findAll(pageable);
    }

    @Override
    public Page<NoticeDetail> findByType(Integer ntypeId,Pageable pageable) {
        return noticeDetailRepository.findByNtypeId(ntypeId,pageable);
    }

    @Override
    public NoticeDetail save(NoticeDetail noticeDetail) {
        return noticeDetailRepository.save(noticeDetail);
    }

    @Override
    public Page<NoticeDetail> findByNDtlTitle(String ndtlName,Pageable pageable) {
        return noticeDetailRepository.findByNdtlTitleContaining(ndtlName,pageable);
    }

    @Override
    public Page<NoticeDetail> findByNdtlStatus(Integer ndtlStatus, Pageable pageable) {
        return noticeDetailRepository.findByNdtlStatus(ndtlStatus,pageable);
    }

    @Override
    public NoticeDetail findOne(String ndtlId) {
        return noticeDetailRepository.findById(ndtlId).orElse(null);
    }
}
