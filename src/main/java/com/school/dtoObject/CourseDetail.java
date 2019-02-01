package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class CourseDetail {

    @Id
    private String coursedtlId;

    /**教室Id*/
    private Integer classroomId;

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
