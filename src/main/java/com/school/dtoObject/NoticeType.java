package com.school.dtoObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class NoticeType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int ntypeId;

    /** 类别名称     */
    private String ntypeName;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;
}
