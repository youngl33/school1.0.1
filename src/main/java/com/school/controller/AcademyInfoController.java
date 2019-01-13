package com.school.controller;


import com.school.service.AcademyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/academy")
public class AcademyInfoController {

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/list")
    public String findAll(){
        return "teacher/index";

    }
}
