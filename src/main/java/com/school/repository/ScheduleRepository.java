package com.school.repository;

import com.school.dtoObject.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,String> {


}
