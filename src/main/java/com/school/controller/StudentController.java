package com.school.controller;

import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Student;
import com.school.service.AcademyInfoService;
import com.school.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.List;


@RequestMapping("/student")
@Controller
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "page",defaultValue = "0") Integer page,
                        @RequestParam(value = "studentStatus",defaultValue = "在读") String studentStatus,
                        @RequestParam(value = "ainfoId",defaultValue = "") String ainfoId,
                        Model model){
        //所有学院信息
        List<AcademyInfo> academyInfoList = academyInfoService.findAll();

        //查找学院
        AcademyInfo academyInfo = new AcademyInfo();
        academyInfo.setAinfoId(ainfoId);
        if(!StringUtils.isEmpty(ainfoId)){
            academyInfo = academyInfoService.findOne(ainfoId);
        }
        PageRequest request = new PageRequest(page,20);
        Page<Student> studentPage=studentService.findByStatus(request,studentStatus);
        List<Student> studentList = studentPage.getContent();

        model.addAttribute("academyInfoList",academyInfoList);
        model.addAttribute("studentStatus",studentStatus);
        model.addAttribute("academyInfo",academyInfo);
        return "/student/index";
    }
}
