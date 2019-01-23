package com.school.service.impl;

import com.school.dtoObject.Schedule;
import com.school.enums.ResultEnum;
import com.school.enums.ScheduleEnum;
import com.school.exception.AdminException;
import com.school.repository.ScheduleRepository;
import com.school.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    @Override
    public List<Schedule> findAll() {
        return repository.findAll();
    }

    @Override
    public Schedule findOne(String scheduleId) {
        return repository.findById(scheduleId).orElse(null);
    }

    @Override
    public void deleteById(String scheduleId) {
        Schedule schedule = findOne(scheduleId);
        if(schedule==null){
            throw new AdminException(ScheduleEnum.SCHEDULE_NOT_EXIST);
        }
        repository.deleteById(scheduleId);
    }

    @Override
    public Schedule create(Schedule schedule) {
        return repository.save(schedule);
    }
}
