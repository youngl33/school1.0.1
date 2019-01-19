package com.school.repository;

import com.school.dtoObject.ScheduleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail,String> {

    ScheduleDetail findByScheduledtlYearAndScheduledtlMonthAndScheduledtlDay(Integer year, Integer month,Integer day);

    Page<ScheduleDetail> findByScheduleId(Pageable pageable,String scheduleId);

    List<ScheduleDetail> findByScheduledtlYearAndScheduledtlMonth(int year,int month);

    List<ScheduleDetail> findByScheduledtlAttrNotIn(String attr);

}
