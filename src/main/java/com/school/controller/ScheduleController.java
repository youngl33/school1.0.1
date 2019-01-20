package com.school.controller;

import com.school.dto.CalendarDTO;
import com.school.dtoObject.Schedule;
import com.school.dtoObject.ScheduleDetail;
import com.school.exception.AdminException;
import com.school.service.ScheduleDetailService;
import com.school.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/index")
    public String index(Model model){

        List<Schedule> scheduleList = scheduleService.findAll();
        model.addAttribute("scheduleList",scheduleList);
        return "/schedule/index";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "scheduleId",defaultValue = "")String scheduleId,
                       Model model){
        Schedule schedule = scheduleService.findOne(scheduleId);
        PageRequest request = new PageRequest(page,15);
        Page<ScheduleDetail> scheduleDetails = scheduleDetailService.findByScheduleId(request,scheduleId);
        model.addAttribute("scheduleSemester",schedule.getScheduleSemester());
        model.addAttribute("scheduleDetails",scheduleDetails);
        model.addAttribute("scheduleId",scheduleId);
        return "/schedule/list";
    }


    @GetMapping("/index/delete")
    public String delete(@RequestParam(value = "scheduleId",required = true) String scheduleId,
                         Model model){
        try{
            scheduleDetailService.delete(scheduleId);
        }catch (AdminException e){
            model.addAttribute("url","/schedule/index");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/schedule/index");
        model.addAttribute("msg","删除成功");
        return "/common/success";
    }

    @PostMapping("/import")
    public String importExcel(MultipartFile file,Model model) throws Exception {
        try {
            String fileName=file.getOriginalFilename();
            scheduleDetailService.importExcel(fileName,file);
        }catch (AdminException e){
            model.addAttribute("url","/schedule/index");
            model.addAttribute("msg",e.getMessage());
            return "/common/error";
        }
        model.addAttribute("url","/schedule/index");
        model.addAttribute("msg","导入成功");
        return "/common/success";
    }



    @ResponseBody
    @GetMapping("/api/schedule/event")
    public List<CalendarDTO> event(){
        List<ScheduleDetail> scheduleDetailList = scheduleDetailService.findByScheduledtlAttrNotIn("");
        List<CalendarDTO> calendarDTOList = new ArrayList<CalendarDTO>();
        for(ScheduleDetail scheduleDetail : scheduleDetailList){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setStartDate(scheduleDetail.getScheduledtlYear().toString()+"-"+scheduleDetail.getScheduledtlMonth()+"-"+scheduleDetail.getScheduledtlDay());
            calendarDTO.setName(scheduleDetail.getScheduledtlAttr());
            calendarDTOList.add(calendarDTO);
        }
        return calendarDTOList;
    }


}
