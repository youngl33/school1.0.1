package com.school.dtoObject;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Schedule {

    @Id
    private String scheduleId;

    /**  学年信息  */
    private String scheduleSemester;

    /** 描述     */
    private String scheduleDescription;

    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;

}
