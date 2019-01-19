package com.school.service;

import com.school.dtoObject.Schedule;

import java.util.List;

public interface ScheduleService {

    /** 查找所有校历    */
    public List<Schedule> findAll();

    /** 通过id查找校历  */
    public Schedule findOne(String scheduleId);

    void deleteById(String scheduleId);

    Schedule create(Schedule schedule);
}
