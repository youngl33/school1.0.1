package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@DynamicUpdate
@Data
@Entity
public class BbsComment {

    @Id
    private String comId;

    /** 评论内容     */
    private String comContent;

    /** 文章内容     */
    private String artId;

    /** 用户id       */
    private String comUserId;

    /** 创建时间     */
    private Date createTime;

    /** 更新时间     */
    private Date updateTime;
}
