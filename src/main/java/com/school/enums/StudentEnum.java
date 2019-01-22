package com.school.enums;

import lombok.Getter;

@Getter
public enum StudentEnum {
    STUDENT_ID_ERROR(0,"学号格式错误"),
    STUDENT_PASSWORD_ERROR(1,"密码格式错误"),
    STUDENT_NAME_ERROR(2,"姓名错误"),
    STUDENT_GENDER_ERROR(3,"性别格式错误"),
    STUDENT_TEL_ERROR(4,"联系方式格式错误"),
    STUDENT_BORNDATE_ERROR(5,"出生日期格式错误"),
    STUDENT_AVATER_ERROR(6,"头像格式错误"),
    STUDENT_ICARD_ERROR(7,"身份证格式错误"),
    CLASSID_ERROR(8,"班级格式错误"),
    AINFO_NAME_ERROR(9,"学院名字格式错误"),
    MAJOR_NAME_ERROR(10,"专业名字格式错误"),
    STUDENT_ADDR_ERROR(11,"地址格式错误")
    ;

    private Integer code;

    private String message;

    StudentEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
