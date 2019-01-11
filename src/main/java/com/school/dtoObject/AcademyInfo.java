package com.school.dtoObject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@DynamicUpdate
@Data
@Entity

public class AcademyInfo {

    @Id
    private String ainfoId;
    /** 学院名字1 */
    private String ainfoName;
    /** 学院描述信息 */
    private String ainfoDescription;
    /** 创建时间 */
    private Timestamp createTime;
    /** 更新时间 */
    private Timestamp updateTime;


}
