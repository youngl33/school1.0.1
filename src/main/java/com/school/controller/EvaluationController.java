package com.school.controller;

import com.school.dto.EvaluationDTO;
import com.school.dtoObject.Evaluation;
import com.school.service.EvaluationService;
import com.school.service.RedisService;
import com.school.service.StudentService;
import com.school.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/evaluation")
@Controller
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    RedisService redisService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "orderBy",defaultValue = "1") Integer orderBy,
                        @RequestParam(value = "page",defaultValue = "0") Integer page,
                        Model model){
        PageRequest request = PageRequest.of(page,15);
        Page<Evaluation> evaluationPage=null;
        if(orderBy==1){
            evaluationPage=evaluationService.findOrderByElaGradeAsc(request);
        }else{
            evaluationPage=evaluationService.findOrderByElaGradeDesc(request);
        }
        List<EvaluationDTO> evaluationDTOList = new ArrayList<>();
        for(Evaluation evaluation: evaluationPage){
            EvaluationDTO evaluationDTO = new EvaluationDTO();
            BeanUtils.copyProperties(evaluation, evaluationDTO);
            evaluationDTO.setTeacherName(teacherService.findOne(evaluationDTO.getTeacherId()).getTeacherName());
            evaluationDTO.setStudentName(studentService.findById(evaluationDTO.getStudentId()).getStudentName());
            evaluationDTOList.add(evaluationDTO);
        }
        model.addAttribute("evaluationDTOs",evaluationDTOList);
        model.addAttribute("page",page);
        model.addAttribute("orderBy",orderBy);
        model.addAttribute("totalPage",evaluationPage.getTotalPages());
        return "/evaluation/index";
    }

    @GetMapping("/publish")
    public String publish(@RequestParam(value = "status",defaultValue = "0")Integer status,
                          Model model){
        String publishEvaluation = redisService.getStr("publishEvaluation");
        model.addAttribute("amount",publishEvaluation);
        if(publishEvaluation==null){
            model.addAttribute("status",0); //1表示已开始 0 表示未开启
        }else{
            model.addAttribute("status",1); //1表示已开始 0 表示未开启
        }
        return "/evaluation/publish";
    }

    @GetMapping("/publish/save")
    public String publishSave(@RequestParam("amount")Integer amount,
                              @RequestParam("status")Integer status,
                              Model model){
        String publishEvaluation = redisService.getStr("publishEvaluation");
        if(publishEvaluation!=null){
            redisService.del("publishEvaluation");
            model.addAttribute("url", "/evaluation/index");
            model.addAttribute("msg", "关闭考评成功");
            return "/common/success";
        }
        redisService.setStr("publishEvaluation","true",amount*3600);
        model.addAttribute("url", "/evaluation/index");
        model.addAttribute("msg", "发布考评成功");
        return "/common/success";
    }
}
