package com.school.dto;

import com.school.dtoObject.Class;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class MajorDTO {
    @Id
    private String majorId;

    /** 专业名字  */
    private String majorName;

    /** 学院ID  */
    private String ainfoId;

    /** 学院名字  */
    private String ainfoName;

    /** 学院描述   */
    private String majorDescription;

    /** 班级   */
    private List<Class> classList;
}
