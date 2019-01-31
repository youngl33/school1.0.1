package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
public class Class {

    /** 班级号     */
    @Id
    private String classId;

    /** 专业Id     */
    private String majorId;

    /** 年级     */
    private Integer classGrade;

    /** 班级人数  */
    private Integer classNum;

    /** 专业名称  */
    private String majorName;

    /** 学院名称  */
    private String ainfoName;

    /** 辅导员名字    */
    private String teacherName;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;

}
