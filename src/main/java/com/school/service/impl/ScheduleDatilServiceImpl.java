package com.school.service.impl;

import com.school.dtoObject.Schedule;
import com.school.dtoObject.ScheduleDetail;
import com.school.enums.ResultEnum;
import com.school.enums.ScheduleEnum;
import com.school.exception.AdminException;
import com.school.repository.ScheduleDetailRepository;
import com.school.service.ScheduleDetailService;
import com.school.service.ScheduleService;
import com.school.utils.ExcelImportUtils;
import com.school.utils.KeyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScheduleDatilServiceImpl implements ScheduleDetailService {

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public Page<ScheduleDetail> findByScheduleId(Pageable pageable, String scheduleId) {
        return scheduleDetailRepository.findByScheduleId(pageable, scheduleId);
    }

    @Override
    public ScheduleDetail findByYearAndMonthAndDay(Integer year, Integer month, Integer day) {
        return scheduleDetailRepository.findByScheduledtlYearAndScheduledtlMonthAndScheduledtlDay(year, month, day);
    }

    @Override
    public List<ScheduleDetail> findByScheduledtlAttrNotIn(String attr) {
        return scheduleDetailRepository.findByScheduledtlAttrNotIn(attr);
    }

    @Override
    public ScheduleDetail findOne(String scheduledtlId) {
        return scheduleDetailRepository.findById(scheduledtlId).orElse(null);
    }

    @Override
    public ScheduleDetail save(ScheduleDetail scheduleDetail) {
        return scheduleDetailRepository.save(scheduleDetail);
    }

    @Transactional
    @Override
    public void delete(String scheduleId) {
        PageRequest pageRequest = PageRequest.of(0,200);
        Page<ScheduleDetail> scheduleDetailPage = scheduleDetailRepository.findByScheduleId(pageRequest,scheduleId);
        List<ScheduleDetail> scheduleDetailList = scheduleDetailPage.getContent();
        for (ScheduleDetail scheduleDetails : scheduleDetailList) {
            ScheduleDetail scheduleDetail = findOne(scheduleDetails.getScheduledtlId());
            if (scheduleDetail == null) {
                throw new AdminException(ResultEnum.DATE_NOT_EXIST);
            }
            scheduleDetailRepository.delete(scheduleDetail);
        }
        scheduleService.deleteById(scheduleId);
    }

    @Transactional
    @Override
    public void importExcel(String fileName, MultipartFile file) throws Exception{
        Workbook wb = ExcelImportUtils.importFile(fileName,file);
        Sheet sheet = wb.getSheetAt(0);
        //ID
        Row row0 =sheet.getRow(0);
        row0.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        String scheduleId = row0.getCell(0).getStringCellValue();
        Schedule schedule = scheduleService.findOne(scheduleId);
        if(schedule!=null){
            throw new AdminException(ScheduleEnum.SCHEDULE_EXIST);
        }else{
            schedule= new Schedule();
        }
        schedule.setScheduleId(scheduleId);
        if(schedule.getScheduleId().isEmpty()){
            throw new AdminException(ScheduleEnum.SCHEDULE_ID_ERROR);
        }
        //学年
        row0.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
        schedule.setScheduleSemester(row0.getCell(1).getStringCellValue());
        if(schedule.getScheduleSemester().isEmpty()){
            throw new AdminException(ScheduleEnum.SCHEDULE_SEMESTER_ERROR);
        }
        //描述信息
        row0.createCell(2).setCellType(Cell.CELL_TYPE_STRING);
        schedule.setScheduleDescription(row0.getCell(2).getStringCellValue());
        Schedule result = scheduleService.create(schedule);
        if(result==null){
            throw new AdminException(ScheduleEnum.SCHEDULE_UPLOAD_ERROR);
        }

        //将校历每天信息存入数据库中
        List<ScheduleDetail> scheduleDetailList = new ArrayList<ScheduleDetail>();
        for(int r = 2;r<=sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            if(row==null){
                continue;
            }

            ScheduleDetail scheduleDetail = new ScheduleDetail();

            //年
            row.getCell(0).setCellType(Cell.CELL_TYPE_NUMERIC);
            scheduleDetail.setScheduledtlYear((int)row.getCell(0).getNumericCellValue());
            if(scheduleDetail.getScheduledtlYear()==null){
                throw new AdminException(1,"导入失败(第"+(r+1)+"行,年格式错误)");
            }
            //月
            row.getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
            scheduleDetail.setScheduledtlMonth((int)row.getCell(1).getNumericCellValue());
            if(scheduleDetail.getScheduledtlMonth()==null){
                throw new AdminException(1,"导入失败(第"+(r+1)+"行,月格式错误)");

            }
            //日
            row.getCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
            scheduleDetail.setScheduledtlDay((int)row.getCell(2).getNumericCellValue());
            if(scheduleDetail.getScheduledtlDay()==null){
                throw new AdminException(1,"导入失败(第"+(r+1)+"行,日格式错误)");

            }
            //星期几
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            scheduleDetail.setScheduledtlWeektoday(row.getCell(3).getStringCellValue());
            if(scheduleDetail.getScheduledtlWeektoday()==null||scheduleDetail.getScheduledtlWeektoday().isEmpty()){
                throw new AdminException(1,"导入失败(第"+(r+1)+"行,星期几格式错误)");
            }
            //第几周
            row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
            scheduleDetail.setScheduledtlWeek((int)row.getCell(4).getNumericCellValue());
            if(scheduleDetail.getScheduledtlWeek()==null){
                throw new AdminException(1,"导入失败(第"+(r+1)+"行,第几周格式错误)");
            }
            //当天描述信息
            if(row.getPhysicalNumberOfCells()==6){
                scheduleDetail.setScheduledtlAttr(row.getCell(5).getStringCellValue());
            }


            //id
            scheduleDetail.setScheduledtlId(KeyUtils.uniqueKey());
            //校历ID
            scheduleDetail.setScheduleId(scheduleId);

            scheduleDetailList.add(scheduleDetail);
        }
        for(ScheduleDetail scheduleDetail : scheduleDetailList){
            save(scheduleDetail);
        }


    }
}
