package com.school.vo;

import com.school.dtoObject.Major;
import lombok.Data;

import java.util.List;

@Data
public class AcademyVO {

    private String ainfoId;

    private String ainfoName;

    private List<MajorVO> majors;
}
