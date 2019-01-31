package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
public class Tax {

    @Id
    private String taxId;

    /**上课周数*/
    private String taxWeek;

    /**选课人数*/
    private Integer availableNum;

    /**科目Id*/
    private String subjectId;

    /**科目名*/
    private String subjectName;

    /**教授本课教师Id*/
    private String teacherId;

    /**学时*/
    private Integer taxTime;

    /**学分*/
    private Double taxScore;

    /**教师名字*/
    private String teacherName;

    /**学年信息*/
    private String scheduleSemester;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
