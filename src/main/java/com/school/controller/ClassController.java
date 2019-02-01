package com.school.controller;

import com.school.dtoObject.Class;
import com.school.dtoObject.Major;
import com.school.exception.AdminException;
import com.school.service.ClassService;
import com.school.service.MajorService;
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
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    public ClassService classService;

    @Autowired
    public MajorService majorService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "page",defaultValue = "0") Integer page,
                        Model model){
        PageRequest request = PageRequest.of(page, 15);
        Page<Class> classPage =null;
        classPage = classService.findAll(request);
        model.addAttribute("classes",classPage);
        return "/classes/index";
    }

    @PostMapping("/profile")
    public String profile(@RequestParam(value = "classId",required = true) String classId,
                          Model model){
        Class cla = classService.find(classId);
        model.addAttribute("class",cla);
        return "/class/profile";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "classId",defaultValue = "") String classId,
                       Model model){
        Class cla = null;
        if(!StringUtils.isEmpty(classId)){
            cla = classService.find(classId);
        }
        model.addAttribute("class",cla);
        return "/classes/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "classId",defaultValue = "")String classId,
                         Model model){
        if (StringUtils.isEmpty(classId)) {
            model.addAttribute("msg", "请选择要删除的班级");
            model.addAttribute("url", "/class/index");
            return "common/error";
        }
        try{
            classService.delete(classId);
        }catch (AdminException e){
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/class/index");
            return "common/error";
        }
        model.addAttribute("msg", "删除成功");
        model.addAttribute("url", "/class/index");
        return "common/success";
    }

    @PostMapping("/edit/save")
    public String save(@Valid Class cla, Model model) {
        Class result = classService.find(cla.getClassId());
        if(result!=null){
            BeanUtils.copyProperties(cla,result);
        }
        Major major = majorService.findByMajorName(cla.getMajorName());
        if(major==null){
            model.addAttribute("msg", cla.getMajorName()+"专业不存在");
            model.addAttribute("url", "/class/edit");
            return "common/error";
        }
        try {
            result.setMajorId(major.getMajorId());
            classService.create(result);
        }catch (AdminException e){
            model.addAttribute("code","200001");
            model.addAttribute("msg", "未知错误");
            model.addAttribute("url", "/student/edit");
            return "common/fail";
        }
        model.addAttribute("msg", "添加成功");
        model.addAttribute("url", "/class/index");
        return "/common/success";
    }

    @GetMapping("/import")
    public String importExcel(){
        return "/classes/import";
    }

    @PostMapping("/import/save")
    public String importSave(MultipartFile file, Model model) throws Exception {

        String fileName= file.getOriginalFilename();
        try {
            classService.importClasses(fileName,file);
        }catch (AdminException e){
            model.addAttribute("url","/class/import");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/class/index");
        model.addAttribute("msg","导入成功");
        return "/common/success";
    }

}
