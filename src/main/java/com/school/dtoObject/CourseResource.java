package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

@DynamicUpdate
@Entity
@Data
public class CourseResource {

    @Id
    private String resId;

    /** 课程表id主键   */
    private String courseId;

    /** 1:ppt,2:word,3:视频   */
    private Integer resAttribute;

    /** 资源地址   */
    private String resAddr;

    /** 描述信息   */
    private String resDescription;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
