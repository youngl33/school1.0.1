package com.school.dtoObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Student {

    /**  学号         */
    @Id
    private String studentId;

    /** 学生名字        */
    private String studentName;

    /** 学生密码        */
    private String studentPassword;

    /** 学生性别        */
    private String studentGender;

    /** 学生电话        */
    private String studentTel;

    /** 出生日期      */
    private Date studentBorndate;

    /** 学生地址        */
    private String studentAddr;

    /** 班级        */
    private String classId;

    /** 学生头像        */
    private String studentAvater;

    /** 身份证号码        */
    private String studentIcard;

    /** 专业名称       */
    private String majorName;

    /** 学院名称       */
    private String ainfoName;
    /** 描述信息        */
    private String studentDescription;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
