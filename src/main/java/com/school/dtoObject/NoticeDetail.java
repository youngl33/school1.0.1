package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
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

    /** 文章内容       */
    private String ndtlContent;

    /** 1:发送 2:删除   */
    private Integer ndtlStatus=1;

    /** 为0时表示学校,其他填入学院ID */
    private String noticeBelong="0";

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
