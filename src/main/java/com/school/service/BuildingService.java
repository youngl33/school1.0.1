package com.school.service;

import com.school.dtoObject.Building;

import java.util.List;

public interface BuildingService {

    /**查找某个教学楼信息*/
    Building findOne(Integer buildingId);

    /**查找所有教学楼信息*/
    List<Building> findAll();

    /**保存教学楼信息*/
    Building save(Building building);

    /**通过教学楼名查找教学楼*/
    Building findByBuildingName(String buildingName);
}
