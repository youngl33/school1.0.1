package com.school.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    IMG_ERROR(2,"图片错误"),
    DATE_NOT_EXIST(3,"日期不存在"),
    SCHEDULE_NOT_EXIST(4,"校历不存在")
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
