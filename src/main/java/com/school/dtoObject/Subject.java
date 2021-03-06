package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@DynamicUpdate
public class Subject {

    @Id
    private String subjectId;

    /**科目名*/
    private String subjectName;

    /**开课学院*/
    private String ainfoId;

    /**科目描述*/
    private String subjectDescription;

    /***创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
