package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
public class AcademyAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aadmId;

    /**账号*/
    private String aadmUsername;

    /**密码*/
    private String aadmPassword;

    /**头像*/
    private String aadmAvater;

    /**昵称*/
    private String aadmName;

    /**管理学院Id*/
    private String ainfoId;

    /**管理学院名*/
    private String ainfoName;

    /**创建时间*/
    private Timestamp createTime;

    /**更新时间*/
    private Timestamp updateTime;

}
