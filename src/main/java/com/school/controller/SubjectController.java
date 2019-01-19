package com.school.controller;

import com.school.dto.SubjectDTO;
import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Subject;
import com.school.service.AcademyInfoService;
import com.school.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/subject")
@Controller
@Slf4j

public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/find")
    public String findSubject(@RequestParam(value = "page",defaultValue = "0")Integer page,
                              @RequestParam(value="ainfoId",defaultValue ="") String ainfoId,
                              @RequestParam(value = "subjectName",defaultValue ="") String subjectName,
                              Model model){
        //1.查找所有学院
        List<AcademyInfo> academyInfoList  = academyInfoService.findAll();

        //2.检查是否进行搜索
        List<SubjectDTO> subjectDTOList=new ArrayList<SubjectDTO>();
        PageRequest request=new PageRequest(page,20);
        if(!StringUtils.isEmpty(ainfoId)&&StringUtils.isEmpty(subjectName)) {
            Page<Subject> subjectPage = subjectService.findByAinfoId(request, ainfoId);
            List<Subject> subjects = subjectPage.getContent();
            for (Subject subject : subjects) {
                SubjectDTO subjectDTO = new SubjectDTO();
                BeanUtils.copyProperties(subject, subjectDTO);
                AcademyInfo academyInfo = academyInfoService.findOne(subject.getAinfoId());
                subjectDTO.setAinfoName(academyInfo.getAinfoName());
                subjectDTOList.add(subjectDTO);
            }
        }
        if(!StringUtils.isEmpty(subjectName)&&StringUtils.isEmpty(ainfoId)){
             Page<Subject>  subjectPage1=subjectService.findBySubjectNameContainning(request,subjectName);
             List<Subject> subjects1=subjectPage1.getContent();
             for(Subject subject1:subjects1){
                 SubjectDTO subjectDTO1=new SubjectDTO();
                 BeanUtils.copyProperties(subject1,subjectDTO1);
                 subjectDTOList.add(subjectDTO1);
             }
        }
        if(!StringUtils.isEmpty(subjectName)&&!StringUtils.isEmpty(ainfoId)){
            Page<Subject> subjectPage2=subjectService.findByAinfoIdAndSubjectNameContaining(request,ainfoId,subjectName);
            List<Subject> subjects2=subjectPage2.getContent();
            for(Subject subject2:subjects2){
                SubjectDTO subjectDTO2=new SubjectDTO();
                BeanUtils.copyProperties(subject2,subjectDTO2);
                subjectDTOList.add(subjectDTO2);
            }

        }

        if(StringUtils.isEmpty(subjectName)&&StringUtils.isEmpty(ainfoId)){
            Page<Subject> subjectPage3=subjectService.findAll(request);
            List<Subject> subjects3=subjectPage3.getContent();
            for(Subject subject3:subjects3){
                SubjectDTO subjectDTO3=new SubjectDTO();
                BeanUtils.copyProperties(subject3,subjectDTO3);
                subjectDTOList.add(subjectDTO3);
            }
        }
        model.addAttribute("subjects",subjectDTOList);
        model.addAttribute("academys",academyInfoList);
        return "/subject/index";

    }

    @GetMapping("/edit")
    public String subjectEdit(@RequestParam(value="subjectId")String subjectId,
                              Model model){
        Subject subject=subjectService.findOne(subjectId);
        AcademyInfo academyInfo=academyInfoService.findOne(subject.getAinfoId());
        List<AcademyInfo> ainfos=academyInfoService.findAll();
        List<AcademyInfo> academyInfoList=new ArrayList<AcademyInfo>();
        for(AcademyInfo a:ainfos ){
            academyInfoList.add(a);
        }
        SubjectDTO subjectDTO=new SubjectDTO();
        BeanUtils.copyProperties(subject,subjectDTO);
        subjectDTO.setAinfoName(academyInfo.getAinfoName());
        model.addAttribute("subject",subjectDTO);
        model.addAttribute("academyInfos",academyInfoList);
        return "/subject/edit";
    }

    @PostMapping("/edit/save")
    public String subjectEditSave(@Valid SubjectDTO subjectDTO,
                                  Model model){

        Subject subject=subjectService.findOne(subjectDTO.getSubjectId());
        BeanUtils.copyProperties(subjectDTO,subject);
        subjectService.save(subject);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/subject/find");
        return "/common/success";
    }

    @GetMapping("/add")
    public String subjectAdd(Model model){
        List<AcademyInfo> ainfos=academyInfoService.findAll();
        List<AcademyInfo> academyInfoList=new ArrayList<AcademyInfo>();
        for(AcademyInfo a:ainfos ){
            academyInfoList.add(a);
        }
        model.addAttribute("academyInfos",academyInfoList);
        return"/subject/add";
    }

    @PostMapping("/add/save")
    public String subjectAddSave(@Valid Subject subject,
                                 Model model){
        subjectService.save(subject);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/subject/find");
        return "/common/success";
    }

}
