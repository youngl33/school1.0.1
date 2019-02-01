package com.school.dtoObject;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Course {

    @Id
    private String courseId;

    /**科目Id*/
    private String subjectId;

    /**教师Id*/
    private String teacherId;

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
