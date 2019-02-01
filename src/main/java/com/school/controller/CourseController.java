package com.school.controller;

import com.school.dto.CourseDTO;
import com.school.dto.CourseDetailDTO;
import com.school.dtoObject.*;
import com.school.service.*;
import com.school.utils.KeyUtils;
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

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseDetailService courseDetailService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/find")
    public String find(@RequestParam(value="page",defaultValue = "0")Integer page,
                       @RequestParam(value="scheduleSemester",defaultValue = "")String scheduleSemester,
                       @RequestParam(value = "subjectId",defaultValue = "") String subjectId,
                       @RequestParam(value="teacherId",defaultValue = "")String teacherId,
                       Model model){
        PageRequest request =new PageRequest(page,16);
        List<Schedule> scheduleList=scheduleService.findAll();
        Page<Course> coursePage=null;
        if(!StringUtils.isEmpty(scheduleSemester)){
            if(!StringUtils.isEmpty(subjectId)&&!StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findByScheduleSemesterAndSubjectIdAndTeacherId(request,scheduleSemester,subjectId,teacherId);
            }else if(!StringUtils.isEmpty(subjectId)&&StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findByScheduleSemesterAndSubjectId(request,scheduleSemester,subjectId);
            }else if(StringUtils.isEmpty(subjectId)&&!StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findByScheduleSemesterAndTeacherId(request,scheduleSemester,teacherId);
            }else if(StringUtils.isEmpty(subjectId)&&StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findByScheduleSemester(request,scheduleSemester);
            }
        }else{
            if(!StringUtils.isEmpty(subjectId)&&!StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findBySubjectIdAndTeacherId(request,subjectId,teacherId);
            }else if(!StringUtils.isEmpty(subjectId)&&StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findBySubjectId(request,subjectId);
            }else if(StringUtils.isEmpty(subjectId)&&!StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findByTeacherId(request,teacherId);
            }else if(StringUtils.isEmpty(subjectId)&&StringUtils.isEmpty(teacherId)){
                coursePage=courseService.findAll(request);
            }
        }
        List<Course> courseList=coursePage.getContent();
        List<CourseDTO> courseDTOList=new ArrayList<CourseDTO>();
        for(Course course:courseList){
            CourseDTO courseDTO=new CourseDTO();
            BeanUtils.copyProperties(course,courseDTO);
            Teacher teacher=teacherService.findOne(courseDTO.getTeacherId());
            Subject subject=subjectService.findOne(courseDTO.getSubjectId());
            courseDTO.setTeacherName(teacher.getTeacherName());
            courseDTO.setSubjectName(subject.getSubjectName());
            courseDTOList.add(courseDTO);
        }

        model.addAttribute("courses",courseDTOList);
        model.addAttribute("coursePage",coursePage);
        model.addAttribute("scheduleList",scheduleList);
        model.addAttribute("scheduleSemester",scheduleSemester);
        model.addAttribute("teacherId",teacherId);
        model.addAttribute("subjectId",subjectId);
        return "/course/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value="courseId")String courseId,
                       Model model){
        Course course=courseService.findOne(courseId);
        CourseDTO courseDTO=new CourseDTO();
        BeanUtils.copyProperties(course,courseDTO);
        Teacher teacher=teacherService.findOne(courseDTO.getTeacherId());
        Subject subject=subjectService.findOne(courseDTO.getSubjectId());
        courseDTO.setSubjectName(subject.getSubjectName());
        courseDTO.setTeacherName(teacher.getTeacherName());
        model.addAttribute("course",courseDTO);
        return "/course/edit";
    }

    @PostMapping("/edit/save")
    public String editSave(@Valid CourseDTO courseDTO,
                           Model model){
        Course course=courseService.findOne(courseDTO.getCourseId());
        BeanUtils.copyProperties(courseDTO,course);
        courseService.save(course);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/course/find");
        return "common/success";
    }

    @GetMapping("/studentCourse")
    public String studentCourse(@RequestParam(value = "page",defaultValue ="0") Integer page,
                                @RequestParam(value="courseId")String courseId,
                                Model model){
        PageRequest request=PageRequest.of(page,16);
        Page<StudentCourse> studentCoursePage=studentCourseService.findByCourseId(request,courseId);
        List<StudentCourse> studentCourseList=studentCoursePage.getContent();
        List<Student> studentList=new ArrayList<Student>();
        for(StudentCourse studentCourse:studentCourseList){
            Student student=studentService.findById(studentCourse.getStudentId());
            studentList.add(student);
        }
        model.addAttribute("students",studentList);
        model.addAttribute("studentCoursePage",studentCoursePage);
        model.addAttribute("courseId",courseId);
        return "/course/studentCourse";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<Schedule> scheduleList=scheduleService.findAll();
        model.addAttribute("schedule",scheduleList);
        return "/course/add";

    }

    @PostMapping("/add/save")
    public String addSave(@Valid CourseDTO courseDTO,
                          Model model){
        Subject subject=subjectService.findBySubjectName(courseDTO.getSubjectName());
        courseDTO.setSubjectId(subject.getSubjectId());
        courseDTO.setCourseId(KeyUtils.uniqueKey());
        courseDTO.setCourseNum(0);
        Course course=new Course();
        BeanUtils.copyProperties(courseDTO,course);
        courseService.save(course);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/course/find");
        return "common/success";

    }

    @GetMapping("/courseDetail")
    public String courseDetail(@RequestParam (value="courseId") String courseId,
                               Model model){
        Course course=courseService.findOne(courseId);
        List<CourseDetail> courseDetailList=courseDetailService.findByCourseId(courseId);
        List<CourseDetailDTO> courseDetailDTOList=new ArrayList<CourseDetailDTO>();
        for(CourseDetail courseDetail:courseDetailList){
            CourseDetailDTO courseDetailDTO=new CourseDetailDTO();
            Classroom classroom=classroomService.findOne(courseDetail.getClassroomId());
            Building building=buildingService.findOne(classroom.getBuildingId());
            BeanUtils.copyProperties(courseDetail,courseDetailDTO);
            courseDetailDTO.setClassroomLocation(classroom.getClassroomLocation());
            courseDetailDTO.setBuildingId(classroom.getBuildingId());
            courseDetailDTO.setBuildingName(building.getBuildingName());
            courseDetailDTO.setClassroomNo(classroom.getClassroomNo());
            courseDetailDTOList.add(courseDetailDTO);
        }
        model.addAttribute("course",course);
        model.addAttribute("courseDetail",courseDetailDTOList);
        model.addAttribute("courseId",courseId);
        return "/course/courseDetail";
    }

    @GetMapping("/courseDetail/add")
    public String courseDetailEdit(@RequestParam(value="courseId") String courseId,
                                   @RequestParam(value="courseBegin") Integer courseBegin,
                                   @RequestParam(value="courseEnd") Integer courseEnd,
                                   Model model
                                   ){
        model.addAttribute("courseId",courseId);
        model.addAttribute("courseBegin",courseBegin);
        model.addAttribute("courseEnd",courseEnd);
        return "/course/courseDetailAdd";
    }

    @PostMapping("/courseDetail/add/save")
    public String courseDetailAddSave(@Valid CourseDetailDTO courseDetailDTO,
                                      Model model){
        Building building=buildingService.findByBuildingName(courseDetailDTO.getBuildingName());
        Classroom classroom=classroomService.findByClassroomLocationAndBuildingIdAndClassroomNo(courseDetailDTO.getClassroomLocation(),building.getBuildingId(),courseDetailDTO.getClassroomNo());
        courseDetailDTO.setCoursedtlId(KeyUtils.uniqueKey());
        courseDetailDTO.setClassroomId(classroom.getClassroomId());
        CourseDetail courseDetail=new CourseDetail();
        BeanUtils.copyProperties(courseDetailDTO,courseDetail);
        courseDetailService.save(courseDetail);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/course/find");
        return "common/success";

    }


    @GetMapping("/resource")
    public String rescource(@RequestParam("courseId") String courseId,
                            Model model) {
        Course course = courseService.findOne(courseId);
        if (course == null) {
            model.addAttribute("url", "/course/find");
            model.addAttribute("msg", "课程不存在");
            return "/common/error";
        }
        model.addAttribute("courseId", courseId);
        return "/course/resource_add";
    }

    @PostMapping("/resource/upload")
    public String resource(@Valid CourseResource courseResource,
                           @RequestParam("newAddr") MultipartFile multipartFile,
                           Model model) throws Exception {
        String url = "";
        switch (courseResource.getResAttribute()) {
            case 0:
                url = "ppt";
                break;
            case 1:
                url = "word";
                break;
            default:
                url = "video";
                break;
        }
        courseResource.setResId(KeyUtils.uniqueKey());
        String fileaddr = UploadUtils.uploadImg(multipartFile,url);
        courseResource.setResAddr(fileaddr);
        CourseResource result = courseResourceService.create(courseResource);
        model.addAttribute("url", "/course/find");
        model.addAttribute("msg", "上传成功");
        return "/common/success";
    }

}
