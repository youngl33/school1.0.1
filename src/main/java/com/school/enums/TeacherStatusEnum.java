package com.school.enums;

public enum TeacherStatusEnum {
    WORKING(0,"在职"),
    RESIGNATION(1,"辞职"),
    RETIRE(2,"退休");

    private Integer code;
    private String message;

    TeacherStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

    public Integer getCode(){
        return code;
    }

}
