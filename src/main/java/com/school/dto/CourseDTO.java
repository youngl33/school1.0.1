package com.school.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {

    private String courseId;

    /**科目Id*/
    private String subjectId;

    /**科目名*/
    private String subjectName;

    /**教师Id*/
    private String teacherId;

    /**教师名*/
    private String teacherName;

    /**学时*/
    private int courseTime;

    /**学分*/
    private Double courseScore;

    /**学习人数*/
    private Integer courseNum;

    /**总人数*/
    private Integer courseTotalnum;

    /**课程开始周*/
    private Integer courseBegin;

    /**节课周*/
    private Integer courseEnd;

    /**学年信息*/
    private String scheduleSemester;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
