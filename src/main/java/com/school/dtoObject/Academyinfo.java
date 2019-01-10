package com.school.dtoObject;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
public class Academyinfo {

    @Id
    private String AInfoId;
    private String AInfoName;
    private String AInfoDescription;
    private Timestamp CreateTime;
    private Timestamp UpdateTime;


}
