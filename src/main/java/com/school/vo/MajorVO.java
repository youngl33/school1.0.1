package com.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class MajorVO {

    private String majorId;

    private String majorName;

    private List<ClassVO> classes;
}
