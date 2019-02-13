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
    /** 教师登录id     */
    private String teacherId;

    /** 教师登录密码    */
    private String teacherPassword;

    /** 教师姓名     */
    private String teacherName;

    /** 教师头像     */
    private String teacherAvater;

    /** 教师电话    */
    private String teacherTel;

    /** 教师生日    */
    private Date teacherBorndate;

    /** 教师性别 */
    private String teacherGender;

    /** 教师身份证号  （唯一键） */
    private String teacherIcard;

    /** 教师家庭住址  */
    private String teacherAddr;

    /** 教师职位    */
    private String teacherPosition;

    /** 教师生涯    */
    private String teacherCareer;

    /** 教师所在学院id  （如果老师属于学院就有学院id，如果不属于就没有）  */
    private String ainfoId;

    /** 教师状态  在职 辞职 退休    */
    private String teacherStatus;

    /** 教师描述    */
    private String teacherDescription;

    /** 创建时间   */
    private Date createTime;

    /** 更新时间   */
    private Date updateTime;
}
