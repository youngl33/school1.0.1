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
    private String AInfoName;
    private String AInfoDescription;
    private Timestamp CreateTime;
    private Timestamp UpdateTime;


}
