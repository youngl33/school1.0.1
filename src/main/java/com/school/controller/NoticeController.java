package com.school.controller;


import com.school.dto.NoticeDTO;
import com.school.dto.UploadImgDTO;
import com.school.dtoObject.NoticeDetail;
import com.school.dtoObject.NoticeType;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.service.NoticeDetailService;
import com.school.service.NoticeTypeService;
import com.school.utils.DateFormatUtils;
import com.school.utils.KeyUtils;
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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RequestMapping("/notice")
@Controller
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeDetailService noticeDetailService;

    @Autowired
    private NoticeTypeService noticeTypeService;

    /**
     * 查看所有文章
     * @param page
     * @param model
     * @param typeWeb 1资讯通知 2回收站
     * @return
     * @throws ParseException
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value= "typeWeb",defaultValue = "1") Integer typeWeb,
                        @RequestParam(value = "top-search",defaultValue = "") String search,
                        Model model) throws ParseException {

        PageRequest request = PageRequest.of(page, 6);
        Page<NoticeDetail> noticeDetails = null;
        if(StringUtils.isEmpty(search)){
            noticeDetails = noticeDetailService.findByNdtlStatus(typeWeb,"0", request);
            List<NoticeDetail> noticeDetailList = noticeDetails.getContent();
        }else if(noticeDetails==null){
            noticeDetails = noticeDetailService.findByNdtlAuthor(search,"0",request);
        }
        if(noticeDetails.getTotalElements()==0){
            NoticeType noticeType = noticeTypeService.findByNtypeName(search);
            if(noticeType!=null)
                noticeDetails = noticeDetailService.findByType(noticeType.getNtypeId(),"0",request);
        }
        if(noticeDetails.getTotalElements()==0){
            noticeDetails = noticeDetailService.findByNDtlTitle(search,"0",request);
        }
        List<NoticeType> noticeTypeList = noticeTypeService.findAll();

        //将类别存入map中
        Map<Integer, String> noticeTypeMap = new HashMap<Integer, String>();
        for (NoticeType noticeType : noticeTypeList) {
            noticeTypeMap.put(noticeType.getNtypeId(), noticeType.getNtypeName());
        }

        List<NoticeDTO> noticeDTOList = new ArrayList<NoticeDTO>();
        for (NoticeDetail noticeDetail : noticeDetails) {
            NoticeDTO noticeDTO = new NoticeDTO();
            BeanUtils.copyProperties(noticeDetail, noticeDTO);
            noticeDTO.setNtypeName(noticeTypeMap.get(noticeDTO.getNtypeId()));
            noticeDTO.setUpdateTime(DateFormatUtils.dateConverterFormatString(noticeDetail.getUpdateTime()));
            noticeDTOList.add(noticeDTO);
        }
        model.addAttribute("typeWeb",typeWeb);
        model.addAttribute("noticeDTOs", noticeDTOList);
        model.addAttribute("noticeList",noticeDetails);
        return "notice/index";
    }

    /**
     * 文章编辑页面
     * @param ndtlId
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String edit(@RequestParam(value = "ndtlId", defaultValue = "") String ndtlId,
                       Model model) {

        List<NoticeType> noticeTypeList = noticeTypeService.findAll();

        if (!StringUtils.isEmpty(ndtlId)) {
            NoticeDetail noticeDetail = noticeDetailService.findOne(ndtlId);
            if (noticeDetail != null) {
                model.addAttribute("NoticeDetail", noticeDetail);
            }
            for (NoticeType noticeType : noticeTypeList) {
                if (noticeType.getNtypeId() == noticeDetail.getNtypeId()) {
                    model.addAttribute("ntypeName", noticeType.getNtypeName());
                    break;
                }
            }
        }


        model.addAttribute("noticeTypes", noticeTypeList);
        return "notice/edit";
    }

    /**
     * 文章保存
     * @param ntypeId
     * @param ndtlTitle
     * @param ndtlContent
     * @param ndtlId
     * @param ndtlAuthor
     * @param model
     * @return
     */
    @PostMapping("/edit/save")
    public String save(@RequestParam(value = "ntypeId", required = true) Integer ntypeId,
                       @RequestParam(value = "ndtlTitle", required = true) String ndtlTitle,
                       @RequestParam(value = "ndtlContent", required = true) String ndtlContent,
                       @RequestParam(value = "ndtlId", defaultValue = "") String ndtlId,
                       @RequestParam(value = "ndtlAuthor", defaultValue = "admin") String ndtlAuthor,
                       Model model) {

        NoticeType noticeType = noticeTypeService.findByNtypeId(ntypeId);
        if (noticeType == null) {
            model.addAttribute("msg", ResultEnum.PARAM_ERROR.getMessage());
            model.addAttribute("url", "/notice/edit");
            return "common/error";
        }

        NoticeDetail noticeDetail = new NoticeDetail();
        if (!StringUtils.isEmpty(ndtlId)) {
            noticeDetail = noticeDetailService.findOne(ndtlId);
        } else {
            noticeDetail.setNdtlId(KeyUtils.uniqueKey());
        }
        noticeDetail.setNtypeId(ntypeId);
        noticeDetail.setNdtlTitle(ndtlTitle);
        noticeDetail.setNdtlContent(ndtlContent);
        noticeDetail.setNdtlAuthor(ndtlAuthor);
        //TODO
        noticeDetail.setNoticeId(1);
        noticeDetailService.save(noticeDetail);

        model.addAttribute("msg", "添加成功");
        model.addAttribute("url", "/notice/index");
        return "common/success";
    }

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit/img/save")
    public UploadImgDTO uploadImage(@RequestParam("upload") MultipartFile multipartFile) {
        UploadImgDTO res = new UploadImgDTO();
        res.setUploaded(0);

        if (multipartFile == null || multipartFile.isEmpty())
            return res;

        //生成新的文件名及存储位置
        String fileName = multipartFile.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .concat(fileName.substring(fileName.lastIndexOf(".")));

        String fullPath = "E:/xampp/htdocs/img/eassy/".concat(newFileName);

        try {
            File target = new File(fullPath);
            if (!target.getParentFile().exists()) { //判断文件父目录是否存在
                target.getParentFile().mkdirs();
            }

            multipartFile.transferTo(target);

            String imgUrl = "http://127.0.0.1/img/eassy/".concat(newFileName);

            res.setUploaded(1);
            res.setFileName(fileName);
            res.setUrl(imgUrl);
            return res;
        } catch (IOException ex) {
            log.info("上传图片异常", ex);
        }

        return res;
    }


    /** 删除和恢复  */
    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("ndtlId")String ndtId,
                               Model model){
        NoticeDetail noticeDetail = noticeDetailService.findOne(ndtId);
        if(noticeDetail.getNdtlStatus()==1){
            noticeDetail.setNdtlStatus(2);
            noticeDetailService.save(noticeDetail);
            model.addAttribute("msg", "修改成功");
            model.addAttribute("url", "/notice/index?typeWeb=2");
            return "common/success";
        }else{
            noticeDetail.setNdtlStatus(1);
            noticeDetailService.save(noticeDetail);
            model.addAttribute("msg", "修改成功");
            model.addAttribute("url", "/notice/index?typeWeb=1");
            return "common/success";
        }
    }

    /** 类别管理   */
    @RequestMapping("/type")
    public String type(Model model){
        List<NoticeType> noticeTypeList = noticeTypeService.findAll();
        model.addAttribute("noticeTypes",noticeTypeList);
        return "notice/notice";
    }

    /**类别修改     */
    @GetMapping("/type/save")
    public String typeSave(@RequestParam(value = "ntypeId",defaultValue = "")Integer ntypeId,
                           @RequestParam(value = "ntypeName") String ntypeName,
                           Model model){

        NoticeType noticeType = new NoticeType();

        try{
            if(ntypeId!=null){
                noticeType = noticeTypeService.findByNtypeId(ntypeId);
            }
            noticeType.setNtypeName(ntypeName);
            noticeTypeService.save(noticeType);
        }catch (AdminException e){
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/notice/type");
            return "/common/error";
        }
        model.addAttribute("msg", "修改成功");
        model.addAttribute("url", "/notice/type");
        return "/common/success";

    }


}
