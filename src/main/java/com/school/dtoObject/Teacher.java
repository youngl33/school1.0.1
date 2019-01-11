package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
@DynamicUpdate
public class Teacher {
    @Id
    private String TeacherId;
    /** 教师登录id     */
    private String TeacherPassword;
    /** 教师登录密码    */
    private String TeacherName;
    /** 教师姓名     */
    private Integer TeacherAge;
    /** 教师年龄     */
    private String TeacherAvater;
    /** 教师头像     */
    private String TeacherTel;
    /** 教师电话    */
    private Data TeacherBorndate;
    /** 教师生日    */
    private String TeacherGender;
    /** 教师性别 */
    private String TeacherIcard;
    /** 教师身份证号  （唯一键） */
    private String TeacherAddr;
    /** 教师家庭住址  */
    private String TeacherPosition;
    /** 教师职位    */
    private String TeacherCareer;
    /** 教师生涯    */
    private String AInfoId;
    /** 教师所在学院id  （如果老师属于学院就有学院id，如果不属于就没有）  */
    private Date CreateTime;
    private Date UpdateTime;
    private Integer TeacherStatus;
    /** 教师状态  0：在职 1；辞职 2：退休    */
    private String TeacherDescription;
    /** 教师描述    */
}
