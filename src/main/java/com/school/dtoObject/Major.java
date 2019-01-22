package com.school.dtoObject;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Major {

    @Id
    private String majorId;

    /** 专业名字  */
    private String majorName;

    /** 学院ID  */
    private String ainfoId;

    /** 学院名字  */
    private String ainfoName;

    /** 描述  */
    private String majorDescription;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
