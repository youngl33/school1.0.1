package com.school.dto;

import lombok.Data;

@Data
public class EvaluationDTO {


    private String elaId;

    /** 教师id   */
    private String teacherId;

    /** 教师名字  */
    private String teacherName;

    /** 科目名 */
    private String subjectName;

    /** 学生id   */
    private String studentId;

    /** 学生名字 */
    private String studentName;

    /** 评分   */
    private Integer elaGrade;

    /** 评论   */
    private String elaAssess;

    /** 学年   */
    private String scheduleSemester;
}
