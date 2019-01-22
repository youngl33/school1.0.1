package com.school.dto;

import lombok.Data;


@Data
public class StudentDTO {

    /**  学号         */
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
    private String studentBorndate;

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

}
