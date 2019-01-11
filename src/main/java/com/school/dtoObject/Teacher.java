package com.school.dtoObject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class Teacher {
    @Id
    private String teacherId;
    /** 教师密码*/
    private String teacherPassword;
    /** 教师名字*/
    private String teacherName;
    /** 教师年龄*/
    private Integer teacherAge;
    /** 教师头像*/
    private String teacherAvater;
    /** 教师电话*/
    private String teacherTel;
    /** 教师出生日期*/
    private Date teacherBorndate;
    /** 教师性别*/
    private String teacherGender;
    /** 教师身份证号*/
    private String teacherIcard;
    /** 教师地址*/
    private String teacherAddr;
    /** 教师职务*/
    private String teacherPosition;
    /** 教师生涯*/
    private String teacherCareer;
    /** 教师所属学院id*/
    private String ainfoId;
    /** 教师状态 0：在职 1：辞职 2：退休*/
    private Integer teacherStatus;
    /** 教师描述*/
    private String teacherDescription;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;

}
