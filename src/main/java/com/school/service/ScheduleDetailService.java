package com.school.service;

import com.school.dtoObject.ScheduleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScheduleDetailService {

    /** 通过校历id查找查找  */
    public Page<ScheduleDetail> findByScheduleId(Pageable pageable,String scheduleId);

    /** 通过年月日查找      */
    public ScheduleDetail findByYearAndMonthAndDay(Integer year,Integer month,Integer day);

    /** 通过主键id查找      */
    public ScheduleDetail findOne(String scheduledtlId);

    /** 查找日期属性不为空的数据  */
    List<ScheduleDetail> findByScheduledtlAttrNotIn(String attr);

    /** 删除               */
    void delete(String scheduleId);

    /** 创建               */
    ScheduleDetail save(ScheduleDetail scheduleDetail);

    void importExcel(String fileName, MultipartFile file) throws Exception;
}
