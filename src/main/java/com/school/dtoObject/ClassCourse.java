package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
public class ClassCourse {

    @Id
    private String ccourseId;
    
    /**班级Id*/
    private String classId;
    
    /**学年信息*/
    private String scheduleSemester;
    
    /**创建时间*/
    private Date createTime;
    
    /**更新时间*/
    private Date updateTime;

}
