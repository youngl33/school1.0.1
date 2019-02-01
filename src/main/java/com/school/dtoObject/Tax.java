package com.school.dtoObject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Tax {

    @Id
    private String taxId;

    /**科目Id*/
    private String subjectId;

    /**教授本课教师Id*/
    private String teacherId;

    /**学年信息*/
    private String scheduleSemester;

    /**学时*/
    private Integer taxTime;

    /**学分*/
    private Double taxScore;

    /**总人数*/
    private Integer taxTotalnum;

    /**学习人数*/
    private Integer taxNum;

    /**课程描述*/
    private String taxDescription;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
