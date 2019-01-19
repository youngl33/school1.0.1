package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ScheduleDetail {

    @Id
    private String scheduledtlId;

    /** 校历id 外键   */
    private String scheduleId;

    /** 年           */
    private Integer scheduledtlYear;

    /** 月           */
    private Integer scheduledtlMonth;

    /** 日           */
    private Integer scheduledtlDay;

    /** 星期几        */
    private String scheduledtlWeektoday;

    /** 周           */
    private Integer scheduledtlWeek;

    /** 属性          */
    private String scheduledtlAttr;

    /** 描述           */
    private String scheduledtlDescription;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;

}
