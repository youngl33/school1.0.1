package com.school.controller;

import com.school.dto.MajorDTO;
import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Major;
import com.school.exception.AdminException;
import com.school.service.AcademyInfoService;
import com.school.service.ClassService;
import com.school.service.MajorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/major")
@Controller
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private ClassService classService;

    @Autowired
    private AcademyInfoService academyInfoService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "ainfoId",defaultValue = "") String ainfoId,
                        Model model){
        List<Major> majorList = majorService.findByAinfoId(ainfoId);
        List<MajorDTO> majorDTOList = new ArrayList<>();
        for (Major major : majorList) {
            MajorDTO majorDTO = new MajorDTO();
            BeanUtils.copyProperties(major, majorDTO);
            majorDTO.setClassList(classService.findByMajorId(majorDTO.getMajorId()));
            majorDTOList.add(majorDTO);
        }
        model.addAttribute("majorDTOList",majorDTOList);
        return "/major/index";
    }

    @GetMapping("save")
    public String save(@RequestParam("majorId")String majorId,
                       @RequestParam("majorDescription")String majorDescription,
                       @RequestParam("majorName") String majorName,
                       @RequestParam(value = "ainfoId",required = false) String ainfoId,
                       Model model){
        Major major = majorService.findById(majorId);
        if(major==null){
            major = new Major();
            major.setMajorId(majorId);
            major.setAinfoId(ainfoId);
            major.setAinfoName(academyInfoService.findOne(ainfoId).getAinfoName());
        }
        major.setMajorName(majorName);
        major.setMajorDescription(majorDescription);
        Major result = majorService.create(major);
        if(result ==null){
            model.addAttribute("url","/major/index?ainfoId="+ainfoId);
            model.addAttribute("msg","修改失败");
            return "/common/error";
        }
        model.addAttribute("url","/major/index?ainfoId="+ainfoId);
        model.addAttribute("msg","修改成功");
        return "/common/success";
    }

    @GetMapping("/import")
    public String importMajors(){
        return "/major/import";
    }

    @PostMapping("/import/save")
    public String importSave(MultipartFile file, Model model) throws Exception {

        String fileName= file.getOriginalFilename();
        try {
            majorService.importMajor(fileName,file);
        }catch (AdminException e){
            model.addAttribute("url","/major/import");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/major/index");
        model.addAttribute("msg","导入成功");
        return "/common/success";
    }
}
