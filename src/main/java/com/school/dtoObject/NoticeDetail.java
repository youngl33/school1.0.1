package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class NoticeDetail {

    @Id
    private String ndtlId;

    /** 类别ID          */
    private Integer ntypeId;

    /** 通知标题       */
    private String ndtlTitle;

    /** 作者            */
    private String ndtlAuthor;

    /** 通知者Id           */
    private Integer noticeId;

    /** 学院或学校通知   0:学校 1:学院 */
    private Integer noticeBelong;

    private String ndtlContent;

    private Integer ndtlStatus=1;


    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
