package com.school.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ClassroomDTO {
    private Integer classroomId;

    /** 教室号*/
    private String classroomNo;

    /**所在校区*/
    private String classroomLocation;

    /** 教室所在楼*/
    private String buildingName;

    /** 教室类型*/
    private String classroomType;

    /** 教室座位数*/
    private Integer classroomSeats;

    /** 教室描述信息*/
    private String classroomDescription;

    /**教学楼Id*/
    private Integer buildingId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
