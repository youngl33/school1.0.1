package com.school.dtoObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Evaluation {

    @Id
    private String elaId;

    /** 教师id   */
    private String teacherId;

    /** 科目名 */
    private String subjectName;

    /** 学生id   */
    private String studentId;

    /** 评分   */
    private Integer elaGrade;

    /** 评论   */
    private String elaAssess;

    /** 学年   */
    private String scheduleSemester;

    /** 创建时间  */
    private Date createTime;

    /** 更新时间  */
    private Date updateTime;
}
