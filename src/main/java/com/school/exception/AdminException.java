package com.school.exception;

import com.school.enums.ResultEnum;

public class AdminException extends RuntimeException {

    private Integer code;

    public AdminException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public AdminException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
