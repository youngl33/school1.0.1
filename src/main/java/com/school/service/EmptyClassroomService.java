package com.school.service;

import com.school.dtoObject.Classroom;

import java.util.List;

public interface EmptyClassroomService {


    /**查找空教室方法*/
    List<Classroom> findEmptyClassroom(Integer buildingId,Integer courseBegin,Integer courseEnd,Integer coursedtlDay,Integer coursedltSequence);
}
