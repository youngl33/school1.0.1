package com.school.service;

import com.school.dtoObject.Classroom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmptyClassroomServiceTest {

    @Autowired
    private EmptyClassroomService emptyClassroomService;

    @Test
    public void findEmptyClassroom() {
        List<Classroom> result=emptyClassroomService.findEmptyClassroom(3,15,4,2);
        for(Classroom classroom:result){
            log.info("result={}",classroom);
        }
    }
}