package com.school.controller;


import com.school.dtoObject.AcademyInfo;
import com.school.service.AcademyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/academy")
public class AcademyInfoController {

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/index")
    public ModelAndView findAll(Map<String,Object> map){
        List<AcademyInfo> academyInfoList = academyInfoService.findAll();
        map.put("academyInfos",academyInfoList);
        return new ModelAndView("academy/index");
    }

    @GetMapping("/profile")
    public String profile(@RequestParam("ainfoId") String ainfoId,
                         Model model){
        AcademyInfo academyInfo = academyInfoService.findOne(ainfoId);

        log.info("【查询一个】result={}",academyInfo);
        model.addAttribute("academy",academyInfo);
        return "/academy/profile";
    }

    @PostMapping("/profile/save")
    public String profileSave(@Valid AcademyInfo academyInfo,
                              Model model){
        log.info("【传入Academy对象】，result={}",academyInfo);
        academyInfoService.save(academyInfo);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/academy/list");
        return "/common/success";
    }

    @GetMapping("/add")
    public String add(){
        return "/academy/profile";
    }
}
