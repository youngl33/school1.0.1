package com.school.controller;

import com.school.dto.UAdminDTO;
import com.school.dtoObject.UniversityAdmin;
import com.school.exception.AdminException;
import com.school.service.UniversityAdminSevice;
import com.school.utils.ImgSaveUtil;
import com.school.utils.UploadImgUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/universityadmin")
public class UniversityAdminController {

    @Autowired
    private UniversityAdminSevice universityAdminSevice;


    @GetMapping("/index")
    public String index(Model model){
        List<UniversityAdmin> universityAdminList = universityAdminSevice.findAll();
        model.addAttribute("universityAdmins",universityAdminList);
        return "/uadmin/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "uadmId",defaultValue = "0")Integer uadmId,
                       Model model){
        if (!uadmId.equals("0")) {
            UniversityAdmin universityAdmin = universityAdminSevice.findOne(uadmId);
            model.addAttribute("universityAdmin",universityAdmin);
        }
        return "/uadmin/edit";
    }



    @PostMapping("/edit/save")
    public String save(@Valid UAdminDTO uAdminDTO,
                       @RequestParam("newAvater") MultipartFile multipartFile,

                       Model model) throws Exception {
        UniversityAdmin universityAdmin = new UniversityAdmin();
        if(uAdminDTO.getUadmId()!=null){
            universityAdmin = universityAdminSevice.findOne(uAdminDTO.getUadmId());
        }
        BeanUtils.copyProperties(uAdminDTO,universityAdmin);
        if(!multipartFile.isEmpty()) {
            universityAdmin.setUadmAvater(UploadImgUtils.uploadImg(multipartFile, "admin"));
        }
        UniversityAdmin result = universityAdminSevice.create(universityAdmin);
        if(result ==null){
            model.addAttribute("msg","保存失败");
            model.addAttribute("url","/universityadmin/index");
            return "/common/error";
        }
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/universityadmin/index");
        return "/common/success";
    }

    @GetMapping("/index/delete")
    public String delete(@RequestParam("uadmId") Integer uadmId,
                         Model model) {
        try {
            universityAdminSevice.delete(uadmId);
        } catch (AdminException e) {
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/universityadmin/index");
            return "/common/error";
        }
        model.addAttribute("msg","删除成功!");
        model.addAttribute("url","/universityadmin/index");
        return "/common/success";
    }
}
