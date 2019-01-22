package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.dtoObject.Major;
import com.school.dtoObject.Student;
import com.school.exception.AdminException;
import com.school.service.ClassService;
import com.school.service.MajorService;
import com.school.service.StudentService;
import com.school.utils.DateFormatUtils;
import com.school.utils.ExcelImportUtils;
import com.school.utils.UploadImgUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("/student")
@Controller
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private MajorService majorService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "page",defaultValue = "0") Integer page,
                        @RequestParam(value = "classId",defaultValue = "") String classId,
                        @RequestParam(value = "ainfoName",defaultValue = "") String ainfoName,
                        @RequestParam(value = "majorName",defaultValue = "") String majorName,
                        @RequestParam(value = "top-search",defaultValue = "") String topSearch,
                        Model model) throws ParseException {
        PageRequest request = new PageRequest(page, 15);
        Page<Student> studentPage = null;
        if(!StringUtils.isEmpty(topSearch)){
            studentPage = studentService.findByStudentNameContaining(request,topSearch);
        }else if(!StringUtils.isEmpty(classId)) {
            studentPage = studentService.findByClassId(request,classId);
        }else{
            studentPage = studentService.findAll(request);
        }

        model.addAttribute("studentList",studentPage);
        model.addAttribute("ainfoName",ainfoName);
        model.addAttribute("majorName",majorName);
        model.addAttribute("classId",classId);
        return "/student/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "studentId",defaultValue = "") String studentId,
                       Model model){
        if(!StringUtils.isEmpty(studentId)){
            Student student = studentService.findById(studentId);
            model.addAttribute("student",student);
        }
        return "/student/edit";
    }

    @PostMapping("/edit/save")
    public String save(@Valid StudentDTO studentForm,
                       @RequestParam("teacherNewAvater") MultipartFile file,
                       Model model) throws Exception {
        Student student = new Student();
        BeanUtils.copyProperties(studentForm,student);
        student.setStudentBorndate(DateFormatUtils.dateConverter(studentForm.getStudentBorndate()));
        if (!file.isEmpty()) {
            student.setStudentAvater(UploadImgUtils.uploadImg(file,"studentAvater"));
        }
        Student result = studentService.create(student);
        if(result==null){
            model.addAttribute("msg", "添加失败");
            model.addAttribute("url", "/student/index");
            return "/common/error";
        }
        model.addAttribute("msg", "添加成功");
        model.addAttribute("url", "/student/index");
        return "/common/success";
    }

    @GetMapping("/import")
    public String importFile(){
        return "/student/import";
    }

    @PostMapping("/import/save")
    public String importSave(MultipartFile file,Model model) throws Exception {

        String fileName= file.getOriginalFilename();
        try {
            studentService.importStudent(fileName, file);
        }catch (AdminException e){
            model.addAttribute("url","/student/index");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/student/index");
        model.addAttribute("msg","导入成功");
        return "/common/success";
    }

}
