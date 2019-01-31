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
public class BbsArticle {

    @Id
    private String artId;

    /** 用户id   */
    private String artUserId;

    /** 文章标题   */
    private String artTitle;

    /** 1表示置顶   */
    private int artTop=0;

    /** 访问量   */
    private int artViewer=0;

    /** 评论数   */
    private int artCommenter=0;

    /** 内容   */
    private String artContent;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;

}
