package com.school.dto;

import lombok.Data;

@Data
public class UAdminDTO {

    private Integer uadmId;

    /** 管理员登录名     */
    private String uadmUsername;

    /** 管理员密码     */
    private String uadmPassword;

    /** 管理员名字     */
    private String uadmName;

    /** 管理员头像地址     */
    private String uadmAvater;
}
