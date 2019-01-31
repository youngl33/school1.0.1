package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class UniversityAdmin {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer uadmId;

    /** 管理员登录名     */
    private String uadmUsername;

    /** 管理员密码     */
    private String uadmPassword;

    /** 管理员名字     */
    private String uadmName;

    /** 管理员头像地址     */
    private String uadmAvater;


    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
