package com.school.enums;

import lombok.Getter;

@Getter
public enum TaxEnum {
    SUBJECT_NAME_ERROR(0,"科目名格式错误！"),
    SUBJECT_ID_ERROR(1,"科目ID格式错误！"),
    TEACHER_NAME_ERROR(2,"教师名格式错误！"),
    TEACHER_ID_ERROR(3,"教师ID格式错误！"),
    SCHEDULE_SEMESTER_ERROR(4,"学年信息格式错误！"),
    TAX_WEEK_ERROR(5,"周数格式错误！"),
    AVALIABLE_NUM_ERROR(6,"选课人数格式错误！"),
    TAX_TIME_ERROR(7,"学时格式错误！"),
    TAX_SCORE_ERROR(8,"学分格式错误！")
    ;



    private Integer code;
    private String message;

    TaxEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }


}
