package com.school.controller;

import com.school.dto.TeacherDTO;
import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Teacher;
import com.school.enums.TeacherStatusEnum;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/teacher")
@Controller
@Slf4j
public class TeacherController{

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/status")
    public String findAll(@RequestParam(value = "page",defaultValue="0") Integer page, @RequestParam(value = "teacherStatus",defaultValue = "") Integer teacherStatus , Model model) throws ParseException {
        //通过状态显示老师信息
        PageRequest request=new PageRequest(page,16);
        Page<Teacher> teacherPage=teacherService.findByTeacherStatus(request,teacherStatus);
        List<Teacher> teacherList= teacherPage.getContent();

        //把信息复制到teacherDTO类里
        List<TeacherDTO> teacherDTOList = new ArrayList<TeacherDTO>();
        for(Teacher teacher : teacherList){
            TeacherDTO teacherDTO = new TeacherDTO();
            BeanUtils.copyProperties(teacher,teacherDTO);
            //若有学院id，则查找学院名
            if(teacherDTO.getAinfoId()!=null||teacherDTO.getAinfoId()!=""){
                AcademyInfo academyInfo=academyInfoService.findOne(teacherDTO.getAinfoId());
                teacherDTO.setAinfoName(academyInfo.getAinfoName());
            }else {
                teacherDTO.setAinfoName("无");
            }
            //TODO
            teacherDTO.setTeacherBorndate(DateFormatUtils.dateConverterFormat(teacherDTO.getTeacherBorndate()));
            //若teacher_status：(0：在职； 1：辞职； 2：退休)
            switch (teacherDTO.getTeacherStatus())
            {
                case 0: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.WORKING.getMessage());break;
                case 1: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.RESIGNATION.getMessage()); break;
                case 2: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.RETIRE.getMessage()); break;
                default: teacherDTO.setTeacherStatusWord("null"); break;
            }
            teacherDTOList.add(teacherDTO);
        }

        model.addAttribute("teacherDTOs",teacherDTOList);
        return "teacher/profile";
    }


    @GetMapping("/academy")
    public String findByAcademyIdAndTeacherStatus(@RequestParam(value = "page",defaultValue="0") Integer page,
                                                  @RequestParam(value="academyName",defaultValue ="") String academyName,
                                                  @RequestParam(value = "teacherStatus",defaultValue="0") Integer teacherStatus,
                                                  Model model){
        PageRequest request=new PageRequest(page,16);
        AcademyInfo academyInfo=academyInfoService.findByAinfoName(academyName);
        Page<Teacher> teacherPage=teacherService.findByAcademyIdAndTeacherStatus(request,academyInfo.getAinfoId(),teacherStatus);
        List<Teacher> teacherList=teacherPage.getContent();

        List<TeacherDTO> teacherDTOList =new ArrayList<>();
        for(Teacher teacher:teacherList){
            TeacherDTO teacherDTO=new TeacherDTO();
            BeanUtils.copyProperties(teacher,teacherDTO);
            teacherDTO.setAinfoName(academyInfo.getAinfoId());
            //若teacher_status：(0：在职； 1：辞职； 2：退休)
            switch (teacherDTO.getTeacherStatus())
            {
                case 0: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.WORKING.getMessage());break;
                case 1: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.RESIGNATION.getMessage()); break;
                case 2: teacherDTO.setTeacherStatusWord(TeacherStatusEnum.RETIRE.getMessage()); break;
                default: teacherDTO.setTeacherStatusWord("null"); break;
            }
            teacherDTOList.add(teacherDTO);
        }
        model.addAttribute("teacherDTO",teacherDTOList);
        return "teacher/profile";

    }

}
