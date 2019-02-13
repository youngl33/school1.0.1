package com.school.controller;

import com.school.dtoObject.AcademyAdmin;
import com.school.dtoObject.AcademyInfo;
import com.school.service.AcademyAdminService;
import com.school.service.AcademyInfoService;
import com.school.utils.ImgSaveUtil;
import com.school.utils.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/academyAdmin")
public class AcademyAdminController {

    @Autowired
    private AcademyAdminService academyAdminService;

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/list")
    public String academyAdminList(Model model){

        List<AcademyAdmin> academyAdminList=academyAdminService.findAll();
        model.addAttribute("academyAdmins",academyAdminList);
        return"/academyAdmin/index";

    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "aadmId",defaultValue = "") Integer aadmId,
                       Model model){
        List<AcademyInfo> academyInfoList=academyInfoService.findAll();
        AcademyAdmin academyAdmin=academyAdminService.findOne(aadmId);
        model.addAttribute("academyAdmin",academyAdmin);
        model.addAttribute("academyInfos",academyInfoList);
        return "/academyAdmin/edit";
    }

    @PostMapping("/edit/save")
    public String editSave(@Valid AcademyAdmin academyAdmin,
                           @RequestParam("newAvater") MultipartFile file,
                           Model model) throws Exception {
        log.info("【传来的值】:Value={}",academyAdmin);
        String path=academyAdmin.getAadmAvater();
        if(!file.isEmpty()){
            path=ImgSaveUtil.saveImg(file);
        }
        academyAdmin.setAadmAvater(path);
        AcademyAdmin result = academyAdminService.save(academyAdmin);
        if(result==null){
            model.addAttribute("msg","保存失败");
            model.addAttribute("url","/academyAdmin/list");
            return "/common/error";
        }
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/academyAdmin/list");
        return "/common/success";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<AcademyInfo> academyInfo=academyInfoService.findAll();
        model.addAttribute("academyInfos",academyInfo);
        return "/academyAdmin/add";
    }

    @PostMapping("/add/save")
    public String addSave(@Valid AcademyAdmin academyAdmin,
                          @RequestParam("newAvater") MultipartFile file,
                          Model model) throws Exception {
        AcademyInfo academyInfo=academyInfoService.findOne(academyAdmin.getAinfoId());
        String path=academyAdmin.getAadmAvater();
        if(!file.isEmpty()){
            path=UploadUtils.uploadImg(file,"adademyAdmin");
        }
        academyAdmin.setAadmAvater(path);
        academyAdmin.setAinfoName(academyInfo.getAinfoName());
        AcademyAdmin result = academyAdminService.save(academyAdmin);
        if(result==null){
            model.addAttribute("msg","保存失败");
            model.addAttribute("url","academyAdmin/list");
            return "/common/error";
        }
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/academyAdmin/list");
        return "/common/success";

    }
}
