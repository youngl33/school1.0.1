package com.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class LocationVO {

    private Integer locationId;

    private String classroomLocation;

    private List<BuildingVO> buildings;
}
