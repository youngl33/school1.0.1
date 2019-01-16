package com.school.controller;


import com.school.dto.NoticeDTO;
import com.school.dtoObject.NoticeDetail;
import com.school.dtoObject.NoticeType;
import com.school.enums.ResultEnum;
import com.school.service.NoticeDetailService;
import com.school.service.NoticeTypeService;
import com.school.utils.DateFormatUtils;
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

import java.text.ParseException;
import java.util.*;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Autowired
    private NoticeDetailService noticeDetailService;

    @Autowired
    private NoticeTypeService noticeTypeService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "page",defaultValue = "0")Integer page,
                        Model model) throws ParseException {

        PageRequest request = new PageRequest(page,10);
        Page<NoticeDetail> noticeDetails = noticeDetailService.findAll(request);
        List<NoticeDetail> noticeDetailList = noticeDetails.getContent();
        List<NoticeType> noticeTypeList = noticeTypeService.findAll();

        //将类别存入map中
        Map<Integer,String> noticeTypeMap = new HashMap<Integer, String>();
        for(NoticeType noticeType:noticeTypeList){
            noticeTypeMap.put(noticeType.getNtypeId(),noticeType.getNtypeName());
        }

        List<NoticeDTO> noticeDTOList = new ArrayList<NoticeDTO>();
        for(NoticeDetail noticeDetail:noticeDetailList){
            NoticeDTO noticeDTO = new NoticeDTO();
            BeanUtils.copyProperties(noticeDetail,noticeDTO);
            noticeDTO.setNtypeName(noticeTypeMap.get(noticeDTO.getNtypeId()));
            noticeDTO.setUpdateTime(DateFormatUtils.dateConverterFormatString(noticeDetail.getUpdateTime()));
            noticeDTOList.add(noticeDTO);
        }

        model.addAttribute("noticeDTOs",noticeDTOList);
        return "notice/index";
    }

    @GetMapping("/edit")
    public String edit(Model model){

        List<NoticeType> noticeTypeList = noticeTypeService.findAll();

        model.addAttribute("noticeTypes",noticeTypeList);
        return "notice/edit";
    }

    @PostMapping("/edit/save")
    public String save(@RequestParam(value = "ntypeId",required = true) Integer ntypeId,
                       @RequestParam(value = "ndtlTitle",required = true) String ndtlTitle,
                       @RequestParam(value = "ndtlContent",required = true) String ndtlContent,
                       @RequestParam(value="ndtlId",defaultValue = "") String ndtlId,
                       @RequestParam(value="ndtlAuthor",defaultValue = "admin") String ndtlAuthor,
                       Model model){

        NoticeType noticeType = noticeTypeService.findByNtypeId(ntypeId);
        if(noticeType==null){
            model.addAttribute("msg", ResultEnum.PARAM_ERROR.getMessage());
            model.addAttribute("url","/notice/edit");
            return "common/error";
        }

        NoticeDetail noticeDetail = new NoticeDetail();
        if(!StringUtils.isEmpty(ndtlId)){
            noticeDetail = noticeDetailService.findOne(ndtlId);
        }
        noticeDetail.setNtypeId(ntypeId);
        noticeDetail.setNdtlContent(ndtlContent);
        noticeDetail.setNdtlContent(ndtlContent);
        noticeDetail.setNdtlAuthor(ndtlAuthor);
        noticeDetail.setNoticeId(1);

        model.addAttribute("msg","添加成功");
        model.addAttribute("url","/notice/index");
        return "common/success";
    }

}
