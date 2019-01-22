package com.school.controller;

import com.school.dtoObject.Schedule;
import com.school.dtoObject.Subject;
import com.school.dtoObject.Tax;
import com.school.dtoObject.Teacher;
import com.school.service.ScheduleService;
import com.school.service.SubjectService;
import com.school.service.TaxService;
import com.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.rmi.MarshalledObject;
import java.util.List;
@RequestMapping("/tax")
@Controller
public class TaxController {
    @Autowired
    private TaxService taxService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/find")
    public String find(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value="scheduleSemester", defaultValue ="2018-2019学年第一学期")String scheduleSemester,
                       @RequestParam(value="subjectName",defaultValue = "") String subjectName,
                       @RequestParam(value = "teacherName",defaultValue = "")String teacherName,
                       Model model){
        PageRequest request=new PageRequest(page,3);
        Page<Tax> taxPage=null;
        if(!StringUtils.isEmpty(subjectName)&&!StringUtils.isEmpty(teacherName)){
            taxPage=taxService.findByScheduleSemesterAndSubjectNameContainingAndTeacherName(request,scheduleSemester,subjectName,teacherName);
        }else if(!StringUtils.isEmpty(subjectName)&&StringUtils.isEmpty(teacherName)){
                    taxPage=taxService.findByScheduleSemesterAndSubjectNameContaining(request,scheduleSemester,subjectName);
                }else if(StringUtils.isEmpty(subjectName)&&!StringUtils.isEmpty(teacherName)){
                            taxPage=taxService.findByScheduleSemesterAndTeacherName(request,scheduleSemester,teacherName);
                        }else if(StringUtils.isEmpty(subjectName)&&StringUtils.isEmpty(teacherName)){
                                    taxPage=taxService.findByScheduleSemester(request,scheduleSemester);
                                }
        List<Tax> taxList=taxPage.getContent();
        List<Schedule> scheduleList=scheduleService.findAll();
        model.addAttribute("scheduleList",scheduleList);
        model.addAttribute("scheduleSemester",scheduleSemester);
        model.addAttribute("teacherName",teacherName);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("taxs",taxList);
        model.addAttribute("taxPage",taxPage);
        return"/tax/index";
    }

    @GetMapping("/edit")
    public String editTax(@RequestParam(value = "taxId")String taxId,
                          Model model){
        Tax tax=taxService.findOne(taxId);
        List<Schedule> scheduleList=scheduleService.findAll();
        model.addAttribute("schedule",scheduleList);
        model.addAttribute("tax",tax);
        return "/tax/edit";
    }

    @PostMapping("/edit/save")
    public String editSave(@Valid Tax tax,
                           Model model){
        Teacher teacher=teacherService.findOne(tax.getTeacherId());
        Subject subject=subjectService.findOne(tax.getSubjectId());
        tax.setTeacherName(teacher.getTeacherName());
        tax.setSubjectName(subject.getSubjectName());
        taxService.save(tax);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/tax/find");
        return "/common/success";
    }

}
