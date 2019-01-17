package com.school.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    IMG_ERROR(2,"图片错误")
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
