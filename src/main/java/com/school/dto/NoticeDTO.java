package com.school.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDTO {

    private String ndtlId;

    /** 类别ID          */
    private Integer ntypeId;

    /** 类别名称      */
    private String ntypeName;

    /** 通知标题       */
    private String ndtlTitle;

    /** 作者            */
    private String ndtlAuthor;

    /** 通知者Id           */
    private Integer noticeId;

    /** 文字内容         */
    private String ndtlContent;

    /** 1:发送 2:删除   */
    private Integer ndtlStatus=1;

    /** 创建时间     */
    private String createTime;

    /** 更新信息时间     */
    private String updateTime;
}
