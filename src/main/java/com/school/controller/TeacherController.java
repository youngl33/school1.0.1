package com.school.controller;

import com.school.dto.TeacherDTO;
import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Teacher;
import com.school.service.AcademyInfoService;
import com.school.service.TeacherService;
import com.school.utils.DateFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/teacher")
@Controller
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AcademyInfoService academyInfoService;


    @GetMapping("/find")
    public String findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "teacherStatus", defaultValue = "在职") String teacherStatus,
                          @RequestParam(value = "ainfoId",defaultValue = "") String ainfoId,
                          @RequestParam(value="teacherName",defaultValue = "") String teacherName,
                          Model model) throws ParseException {

        //通过状态显示老师信息
        PageRequest request = new PageRequest(page, 16);
        Page<Teacher> teacherPage=null;

        List<AcademyInfo> ainfo=academyInfoService.findAll();
        List<AcademyInfo> academyInfoList=new ArrayList<AcademyInfo>();
        for(AcademyInfo academyInfo:ainfo ){
            academyInfoList.add(academyInfo);
        }
        if(!StringUtils.isEmpty(teacherName)&&!StringUtils.isEmpty(ainfoId)){
            teacherPage=teacherService.findByTeacherNameContainingAndTeacherStatusAndAinfoId(request,teacherName,teacherStatus,ainfoId);
        }else if(!StringUtils.isEmpty(teacherName)&&StringUtils.isEmpty(ainfoId)){
                   teacherPage=teacherService.findByTeacherNameContainingAndTeacherStatus(request,teacherName,teacherStatus);
                }else if(StringUtils.isEmpty(teacherName)&&!StringUtils.isEmpty(ainfoId)){
                            teacherPage=teacherService.findByAcademyIdAndTeacherStatus(request,ainfoId,teacherStatus);
                        }else if(StringUtils.isEmpty(teacherName)&&StringUtils.isEmpty(ainfoId)){
                                        teacherPage=teacherService.findByTeacherStatus(request,teacherStatus);
                                    }

        List<Teacher> teacherList = teacherPage.getContent();
        List<TeacherDTO> teacherDTOList = new ArrayList<TeacherDTO>();
        for(Teacher teacher : teacherList){
            TeacherDTO teacherDTO = new TeacherDTO();
            BeanUtils.copyProperties(teacher, teacherDTO);
            //若有学院id，则查找学院名
            if (teacherDTO.getAinfoId() != null || teacherDTO.getAinfoId() != "") {
                AcademyInfo ainfo1 = academyInfoService.findOne(teacherDTO.getAinfoId());
                teacherDTO.setAinfoName(ainfo1.getAinfoName());
                } else {
                    teacherDTO.setAinfoName("无");
                }
                teacherDTO.setTeacherBorndate(DateFormatUtils.dateConverterFormatString2(teacher.getTeacherBorndate()));
                teacherDTOList.add(teacherDTO);
            }
        AcademyInfo academySearch=academyInfoService.findOne(ainfoId);
        model.addAttribute("teacherName",teacherName);
        model.addAttribute("teacherPage",teacherPage);
        model.addAttribute("academySearch",academySearch);
        model.addAttribute("teacherStatus", teacherStatus);
        model.addAttribute("ainfoId", ainfoId);
        model.addAttribute("academyInfos",academyInfoList);
        model.addAttribute("teacherDTOs", teacherDTOList);
        return "teacher/index";
    }
//TODO
    @GetMapping("/edit")
    public String teacherEdit(@RequestParam("teacherId") String teacherId,
                              Model model) throws ParseException {
        Teacher teacher=teacherService.findOne(teacherId);
        AcademyInfo academyInfo=academyInfoService.findOne(teacher.getAinfoId());
        List<AcademyInfo> ainfos=academyInfoService.findAll();
        List<AcademyInfo> academyInfoList=new ArrayList<AcademyInfo>();
        for(AcademyInfo a:ainfos ){
            academyInfoList.add(a);
        }
        TeacherDTO teacherDTO=new TeacherDTO();
        BeanUtils.copyProperties(teacher,teacherDTO);
        teacherDTO.setTeacherBorndate(DateFormatUtils.dateConverterFormatString2(teacher.getTeacherBorndate()));
        teacherDTO.setTeacherStatus(teacher.getTeacherStatus());

        model.addAttribute("teacherDTO",teacherDTO);
        model.addAttribute("academyInfo",academyInfoList);
        model.addAttribute("teacher",teacher);
        model.addAttribute("ainfo",academyInfo);
        return "teacher/edit";
    }

    @PostMapping("edit/save")
    public String teacherEditSave(@Valid TeacherDTO teacherDTO,
                                  Model model) throws ParseException {
        log.info("resutl={}",teacherDTO);
        Teacher teacher = teacherService.findOne(teacherDTO.getTeacherId());
        BeanUtils.copyProperties(teacherDTO,teacher);
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter(teacherDTO.getTeacherBorndate()));
        teacherService.save(teacher);

        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/teacher/profile");
        return "common/success";

    }

    @GetMapping("/add")
    public String add(Model model){
        List<AcademyInfo> ainfos=academyInfoService.findAll();
        List<AcademyInfo> academyInfoList=new ArrayList<AcademyInfo>();
        for(AcademyInfo a:ainfos ){
            academyInfoList.add(a);
        }
        model.addAttribute("academyInfo",academyInfoList);
        return "teacher/add";
    }

    @PostMapping("/add/save")
    public String addSave(@Valid TeacherDTO teacherDTO,
                          Model model) throws ParseException {
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacherDTO,teacher);
        Teacher result=teacherService.findOne(teacher.getTeacherId());
        log.info("【查找结果】:result={}",result);
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter(teacherDTO.getTeacherBorndate()));
        teacherService.save(teacher);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/teacher/profile");
        return "common/success";
    }


}
