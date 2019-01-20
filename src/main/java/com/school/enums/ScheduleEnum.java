package com.school.enums;

import lombok.Getter;

@Getter
public enum ScheduleEnum {
    SCHEDULE_EXIST(0,"校历已存在"),
    SCHEDULE_ID_ERROR(1,"校历ID错误"),
    SCHEDULE_SEMESTER_ERROR(2,"学年错误"),
    SCHEDULE_UPLOAD_ERROR(3,"校历上传失败")
    ;

    private Integer code;

    private String message;

    ScheduleEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
