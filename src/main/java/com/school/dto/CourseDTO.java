package com.school.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {

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

    /**日期*/
    private Date courseDate;

    /**节次*/
    private Integer courseSequence;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**科目名*/
    private String subjectName;

    /**上课周*/
    private Integer taxWeek;

    /**教师名*/
    private String  teacherName;
}
