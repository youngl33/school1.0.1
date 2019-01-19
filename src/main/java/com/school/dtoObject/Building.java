package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Building {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer buildingId;

    /**教学楼*/
    private String buildingName;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
