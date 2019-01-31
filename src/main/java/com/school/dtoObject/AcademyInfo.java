package com.school.dtoObject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;


@DynamicUpdate
@Data
@Entity
public class AcademyInfo {

    @Id
    private String ainfoId;
    /** 学院名字 */
    private String ainfoName;
    /** 学院描述信息 */
    private String ainfoDescription;
    /** 创建时间     */
    private Date createTime;

    /** 更新信息时间     */
    private Date updateTime;


}
