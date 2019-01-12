package com.school.controller;

import com.school.dtoObject.Teacher;
import com.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/Teacher")
@Controller

public class TeacherController{

   /* @Autowired
    private TeacherService teacherService;

    @RequestMapping("/List")
    public void findAll(){
        List<Teacher> teacherList=teacherService.findAll();
    }*/
}
