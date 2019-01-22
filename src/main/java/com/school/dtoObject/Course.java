package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    /**课Id*/
    private String taxId;

    /**所在课表Id*/
    private String ccourseId;

    /**上课教室Id*/
    private Integer classroomId;

    /**第几周*/
    private Integer courseWeek;

    /**星期几*/
    private Integer courseDay;
    private Date courseDate;
    private Integer courseSequence;
    private Date createTime;
    private Date updateTime;

}
