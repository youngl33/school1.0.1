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
    private Integer classroomId;

    /** 教室号*/
    private String classroomNo;

    /**所在校区*/
    private String classroomLocation;

    /** 教室所在楼*/
    private Integer buildingId;

    /** 教室类型*/
    private String classroomType;

   /** 教室座位数*/
   private Integer classroomSeats;

    /** 教室描述信息*/
    private String classroomDescription;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;


}
