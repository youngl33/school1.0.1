package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
public class ClassCourse {

    @Id
    private String ccourseId;

    /**课程Id*/
    private String taxId;

    /**班级Id*/
    private String classId;
    
    /**创建时间*/
    private Date createTime;
    
    /**更新时间*/
    private Date updateTime;

}
