package com.school.enums;

import lombok.Getter;

@Getter
public enum ScheduleEnum {
    SCHEDULE_EXIST(1,"校历已存在"),
    SCHEDULE_ID_ERROR(2,"校历ID错误"),
    SCHEDULE_SEMESTER_ERROR(3,"学年错误"),
    SCHEDULE_UPLOAD_ERROR(4,"校历上传失败"),
    SCHEDULE_NOT_EXIST(5,"校历不存在"),

    ;

    private Integer code;

    private String message;

    ScheduleEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
