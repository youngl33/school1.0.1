package com.school.exception;

import com.school.enums.ResultEnum;
import com.school.enums.ScheduleEnum;
import com.school.enums.StudentEnum;

public class AdminException extends RuntimeException {

    private Integer code;

    public AdminException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public AdminException(ScheduleEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public AdminException(StudentEnum studentEnum){
        super(studentEnum.getMessage());
        this.code=studentEnum.getCode();
    }

    public AdminException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
