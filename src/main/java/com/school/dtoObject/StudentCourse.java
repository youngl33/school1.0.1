package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class StudentCourse {

    @Id
    private String scourseId;

    /**学期信息*/
    private String scheduleSemester;

    /**课程Id*/
    private String courseId;

    /**学生Id*/
    private String studentId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
