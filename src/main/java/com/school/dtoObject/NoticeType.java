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
