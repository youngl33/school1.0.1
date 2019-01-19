package com.school.service.impl;

import com.school.dtoObject.ScheduleDetail;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.repository.ScheduleDetailRepository;
import com.school.service.ScheduleDetailService;
import com.school.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleDatilServiceImpl implements ScheduleDetailService {

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public Page<ScheduleDetail> findByScheduleId(Pageable pageable, String scheduleId) {
        return scheduleDetailRepository.findByScheduleId(pageable, scheduleId);
    }

    @Override
    public ScheduleDetail findByYearAndMonthAndDay(Integer year, Integer month, Integer day) {
        return scheduleDetailRepository.findByScheduledtlYearAndScheduledtlMonthAndScheduledtlDay(year, month, day);
    }

    @Override
    public List<ScheduleDetail> findByScheduledtlAttrNotIn(String attr) {
        return scheduleDetailRepository.findByScheduledtlAttrNotIn(attr);
    }

    @Override
    public ScheduleDetail findOne(String scheduledtlId) {
        return scheduleDetailRepository.findById(scheduledtlId).orElse(null);
    }

    @Override
    public ScheduleDetail save(ScheduleDetail scheduleDetail) {
        return scheduleDetailRepository.save(scheduleDetail);
    }

    @Transactional
    @Override
    public void delete(String scheduleId) {
        PageRequest pageRequest = new PageRequest(0,200);
        Page<ScheduleDetail> scheduleDetailPage = scheduleDetailRepository.findByScheduleId(pageRequest,scheduleId);
        List<ScheduleDetail> scheduleDetailList = scheduleDetailPage.getContent();
        for (ScheduleDetail scheduleDetails : scheduleDetailList) {
            ScheduleDetail scheduleDetail = findOne(scheduleDetails.getScheduledtlId());
            if (scheduleDetail == null) {
                throw new AdminException(ResultEnum.DATE_NOT_EXIST);
            }
            scheduleDetailRepository.delete(scheduleDetail);
        }
        scheduleService.deleteById(scheduleId);
    }


}
