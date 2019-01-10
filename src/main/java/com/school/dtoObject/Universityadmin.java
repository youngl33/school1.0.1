package com.school.dtoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class Universityadmin {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer UAdmId;

    private String UAdmUsername;

    private String UAdmPassword;

    private String UAdmName;

    private String UAdmAvater;

    private Integer Identity=0;

    private Date CreateTime;

    private Date UpdateTime;


}
