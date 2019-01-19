package com.school.controller;

import com.school.dto.ClassroomDTO;
import com.school.dtoObject.Building;
import com.school.dtoObject.Classroom;
import com.school.service.BuildingService;
import com.school.service.ClassroomService;
import lombok.extern.slf4j.Slf4j;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/find")
    public String findClassroom(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                @RequestParam(value="classroomLocation",defaultValue ="")String classroomLocation,
                                @RequestParam(value="buildingId",defaultValue ="0")Integer buildingId,
                                @RequestParam(value="classroomType",defaultValue = "")String classroomType,
                                Model model){
        List<Building> buildingListFind=buildingService.findAll();

        List<ClassroomDTO> classroomDTOList=new ArrayList<ClassroomDTO>();
        PageRequest request=new PageRequest(page,40);
        Page<Classroom> classroomPage = classroomService.search(request,classroomLocation,buildingId,classroomType);
        List<Classroom> classroomList=classroomPage.getContent();
        for(Classroom classroom:classroomList){
                ClassroomDTO classroomDTO=new ClassroomDTO();
                Building building=buildingService.findOne(classroom.getBuildingId());
                BeanUtils.copyProperties(classroom,classroomDTO);
                classroomDTO.setBuildingName(building.getBuildingName());
                classroomDTOList.add(classroomDTO);
            }
        model.addAttribute("classroomDTOs",classroomDTOList);
        model.addAttribute("building",buildingListFind);
        return "classroom/index";
    }

    @GetMapping("/edit")
    public String editClassroom(@RequestParam(value="classroomId") Integer classroomId,
                                Model model){

        Classroom classroom=classroomService.findOne(classroomId);
        Building building=buildingService.findOne(classroom.getBuildingId());
        ClassroomDTO classroomDTO=new ClassroomDTO();
        BeanUtils.copyProperties(classroom,classroomDTO);
        classroomDTO.setBuildingName(building.getBuildingName());
        model.addAttribute("building",classroomDTO);
        model.addAttribute("classroom",classroom);
        return "/classroom/edit";
    }

    @PostMapping("/edit/save")
    public String saveClassroomEdit(@Valid Classroom classroom,
                                    Model model){
        log.info("【传来的对象】:Object={}",classroom);
        classroomService.save(classroom);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/teacher/find");
        return "/common/success";
    }

    @GetMapping("/add")
    public String addClassroom(Model model){
        List<Building> buildings=buildingService.findAll();
        List<Building> buildingList=new ArrayList<Building>();
        for(Building building:buildings){
            buildingList.add(building);
        }
        model.addAttribute("buildings",buildingList);
        return "/classroom/add";
    }

    @PostMapping("/add/save")
    public String classroomAddSave(@Valid Classroom classroom,
                                   Model model){
        log.info("【找】:信息={}",classroom);
        classroomService.save(classroom);
        model.addAttribute("msg","保存成功");
        model.addAttribute("url","/classroom/edit");
        return "/common/success";
    }



}
