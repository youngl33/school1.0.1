package com.school.dtoObject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@DynamicUpdate
@Data
@Entity

public class Academyinfo {

    @Id
    private String AInfoId;
    /** 学院名字 */
    private String AInfoName;
    /** 学院描述信息 */
    private String AInfoDescription;
    /** 创建时间 */
    private Timestamp CreateTime;
    /** 更新时间 */
    private Timestamp UpdateTime;


}
