/*
package com.school.controller;

import com.school.dtoObject.ClassCourse;
import com.school.dtoObject.Schedule;
import com.school.service.ClassCourseService;
import com.school.service.ScheduleService;
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

@RequestMapping("/classCourse")
@Controller
public class ClassCourseController {

    @Autowired
    private ClassCourseService classCourseService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/find")
    public String find(@RequestParam(value="page",defaultValue = "0") Integer page,
                       @RequestParam(value = "scheduleSemester",defaultValue = "") String scheduleSemester,
                       @RequestParam(value="classId",defaultValue = "")String classId,
                       Model model){
        List<Schedule> scheduleList=scheduleService.findAll();
        PageRequest request=PageRequest.of(page,10);
        Page<ClassCourse> classCoursePage=null;
        if(!StringUtils.isEmpty(scheduleSemester)&&!StringUtils.isEmpty(classId)){
            classCoursePage=classCourseService.findByScheduleSemesterAndClassId(request,scheduleSemester,classId);
        }else if(!StringUtils.isEmpty(scheduleSemester)&&StringUtils.isEmpty(classId)){
            classCoursePage=classCourseService.findByScheduleSemester(request,scheduleSemester);
        }else if(StringUtils.isEmpty(scheduleSemester)&&!StringUtils.isEmpty(classId)){
            classCoursePage=classCourseService.findByClassId(request,classId);
        }else if(StringUtils.isEmpty(scheduleSemester)&&StringUtils.isEmpty(classId)){
            classCoursePage=classCourseService.findAll(request);
        }
        List<ClassCourse> classCourseList=classCoursePage.getContent();
        model.addAttribute("classCourse",classCourseList);
        model.addAttribute("scheduleSemester",scheduleSemester);
        model.addAttribute("classId",classId);
        model.addAttribute("classCoursePage",classCoursePage);
        model.addAttribute("scheduleList",scheduleList);
        return "/classCourse/index";
    }

    @GetMapping("/view")
    public String view(@RequestParam(value="page" ,defaultValue = "0")Integer page,
                       @RequestParam(value="ccourseId",defaultValue = "")String ccourseId,
                       Model model){

        return "classCourse/view";
    }




}
*/
