package com.school.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    IMG_ERROR(2,"图片错误"),
    DATE_NOT_EXIST(3,"日期不存在"),
    CLASS_NOE_EXIST(202101,"班级不存在"),
    STUDENT_CANTOT_DELETE(202201,"该学生不能删除"),
    UNKNOW_ERROR(200001,"未知错误"),
    FILE_FORMAT_ERROR(206001,"Excel文件格式错误，支持格式.xlsx、.xlsx"),
    FILE_IMPORT_ERROR(206002,"Excel文件导入失败");
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
