package com.school.service;

import com.school.dtoObject.Location;

import java.util.List;

public interface LocationService {

    /**保存*/
    Location save(Location location);

    /**查找*/
    Location findOne(Integer locationId);

    /**通过校区名查找*/
    Location findByClassroomLocation(String classroomLocation);

    /**查找所有校区*/
    List<Location> findAll();
}
