package com.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class BuildingVO {

    private Integer buildingId;

    private String buildingName;

    private List<ClassroomVO> classrooms;
}
