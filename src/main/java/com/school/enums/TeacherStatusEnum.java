package com.school.enums;

public enum TeacherStatusEnum {
    WORKING(1,"在职"),
    RESIGNATION(2,"辞职"),
    RETIRE(3,"退休");

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
