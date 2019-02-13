package com.school.controller;

import com.school.dto.CourseDTO;
import com.school.dto.TeacherDTO;
import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Course;
import com.school.dtoObject.Teacher;
import com.school.exception.AdminException;
import com.school.service.AcademyInfoService;
import com.school.service.CourseService;
import com.school.service.SubjectService;
import com.school.service.TeacherService;
import com.school.utils.DateFormatUtils;
import com.school.utils.ImgSaveUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    SubjectService subjectService;

    @Autowired
    CourseService  courseService;


    @GetMapping("/index")
    public String findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "teacherStatus", defaultValue = "在职") String teacherStatus,
                          @RequestParam(value = "ainfoId",defaultValue = "") String ainfoId,
                          @RequestParam(value="teacherName",defaultValue = "") String teacherName,
                          Model model) throws ParseException {

        //通过状态显示老师信息
        PageRequest request = PageRequest.of(page, 16);
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
                                  @RequestParam("teacherNewAvater") MultipartFile file,
                                  Model model) throws Exception {
        log.info("resutl={}",teacherDTO);
        String path=teacherDTO.getTeacherAvater();
        if (!file.isEmpty()) {
            path = ImgSaveUtil.saveImg(file);
        }
        teacherDTO.setTeacherAvater(path);
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
                          @RequestParam("teacherNewAvater") MultipartFile file,
                          Model model) throws Exception {
        Teacher teacher=new Teacher();
        String path=teacherDTO.getTeacherAvater();
        if (!file.isEmpty()) {
            path = ImgSaveUtil.saveImg(file);
        }
        teacherDTO.setTeacherAvater(path);
        BeanUtils.copyProperties(teacherDTO,teacher);
        Teacher result=teacherService.findOne(teacher.getTeacherId());
        log.info("【查找结果】:result={}",result);
        teacher.setTeacherBorndate(DateFormatUtils.dateConverter(teacherDTO.getTeacherBorndate()));
        teacherService.save(teacher);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/teacher/profile");
        return "common/success";
    }

    @GetMapping("/import")
    public String ImportFile(){return "/teacher/import";}

    @PostMapping("/import/save")
    public String importSave(MultipartFile file,Model model) throws Exception{
        String fileName=file.getOriginalFilename();
        try{
            teacherService.importTeacher(fileName,file);
        }catch(AdminException e)
        {
            model.addAttribute("url","/teacher/index");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/teacher/index");
        model.addAttribute("msg","导入成功！");
        return "/common/success";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam("teacherId")String teacherId,
                          Model model){
        Teacher teacher = teacherService.findOne(teacherId);
        List<Course> courseList = courseService.findByTeacherId(teacherId);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course course:courseList){
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course,courseDTO);
            courseDTO.setSubjectName(subjectService.findOne(course.getSubjectId()).getSubjectName());
            courseDTO.setTeacherName(teacher.getTeacherName());
            courseDTOList.add(courseDTO);
        }
        model.addAttribute("teacher",teacher);
        model.addAttribute("courseList",courseDTOList);
        return "/teacher/profile";
    }

}
