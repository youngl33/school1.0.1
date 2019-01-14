package com.school.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
