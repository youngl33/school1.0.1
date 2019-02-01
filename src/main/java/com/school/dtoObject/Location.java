package com.school.dtoObject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    /**所在校区*/
    private String classroomLocation;

    /**创建时间*/
    private Date createTime;

    /**修改时间*/
    private Date updateTime;

}
