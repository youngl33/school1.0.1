package com.school.controller;


import com.school.dtoObject.AcademyInfo;
import com.school.service.AcademyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/academy")
public class AcademyInfoController {

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/list")
    public ModelAndView findAll(@RequestParam(value="page",defaultValue = "0")Integer page,
                                Map<String,Object> map){
        PageRequest request = new PageRequest(page,15);
        Page<AcademyInfo> academyInfoPage = academyInfoService.findAll(request);
        List<AcademyInfo> academyInfoList = academyInfoPage.getContent();
        map.put("academyInfos",academyInfoList);
        return new ModelAndView("academy/index");
    }
}
