package com.school.dtoObject;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Classroom {
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Id
    private String classroomId;

    /** 教室号*/
    private String classroomNo;

    /** 教室所在楼*/
    private String buildingName;

    /** 教室描述信息*/
    private String classroomDescription;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;


}
