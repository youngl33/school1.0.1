package com.school.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDetailDTO {

    private String coursedtlId;

    /**教室Id*/
    private Integer classroomId;

    /** 教室号*/
    private String classroomNo;

    /**所在校区*/
    private String classroomLocation;

    /** 教室所在楼Id*/
    private Integer buildingId;

    /**教室所在楼*/
    private String buildingName;

    /**课程Id*/
    private String courseId;

    /**周几*/
    private Integer coursedtlDay;

    /**第几节课*/
    private Integer coursedtlSequence;

    /**课程描述信息*/
    private String courseDescription;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
