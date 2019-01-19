package com.school.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubjectDTO {
    private String subjectId;

    /**科目名*/
    private String subjectName;

    /**学时*/
    private Integer subjectTime;

    /**学分*/
    private Double subjectScore;

    /**开课学院*/
    private String ainfoId;

    /**开课学院名*/
    private String ainfoName;

    /**科目描述*/
    private String subjectDescription;

    /***创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
