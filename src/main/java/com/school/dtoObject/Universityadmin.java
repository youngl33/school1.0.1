package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//test
@Data
@Entity
@DynamicUpdate
public class Universityadmin {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer UAdmId;
    /** 管理员登录名     */
    private String UAdmUsername;
    /** 管理员密码     */
    private String UAdmPassword;
    /** 管理员名字     */
    private String UAdmName;
    /** 管理员头像地址     */
    private String UAdmAvater;
    /** 0表示学校管理员     */
    private Integer Identity=0;
    /** 创建时间     */
    private Date CreateTime;
    /** 更新信息时间     */
    private Date UpdateTime;


}
