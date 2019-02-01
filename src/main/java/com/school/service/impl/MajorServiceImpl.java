package com.school.service.impl;

import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Class;
import com.school.dtoObject.Major;
import com.school.dtoObject.Student;
import com.school.exception.AdminException;
import com.school.repository.MajorRepositiory;
import com.school.service.AcademyInfoService;
import com.school.service.MajorService;
import com.school.utils.DateFormatUtils;
import com.school.utils.ExcelImportUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepositiory majorRepositiory;

    @Autowired
    private AcademyInfoService academyInfoService;

    @Override
    public Major create(Major major) {
        return majorRepositiory.save(major);
    }

    @Override
    public Major findById(String majorId) {
        return majorRepositiory.findById(majorId).orElse(null);
    }

    @Override
    public Major findByMajorName(String majorName) {
        return majorRepositiory.findByMajorName(majorName);
    }

    @Override
    public List<Major> findByAinfoId(String ainfoId) {
        return majorRepositiory.findByAinfoId(ainfoId);
    }

    @Override
    public Page<Major> findAll(Pageable pageable) {
        return majorRepositiory.findAll(pageable);
    }

    @Override
    public void importMajor(String fileName, MultipartFile file) throws Exception {
        //检测excel文件格式
        Workbook wb = ExcelImportUtils.importFile(fileName,file);
        Sheet sheet = wb.getSheetAt(0);
        List<Major> majorList = new ArrayList<Major>();
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            Major major = new Major();
            if(row==null){
                continue;
            }
            //编号
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            major.setMajorId(row.getCell(0).getStringCellValue());
            if(StringUtils.isEmpty(major.getMajorId())){
                throw new AdminException(999,"第"+(r+1)+"行编号格式错误");
            }
            //专业名
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            major.setMajorName(row.getCell(1).getStringCellValue());
            if(StringUtils.isEmpty(major.getMajorName())){
                throw new AdminException(999,"第"+(r+1)+"行专业名格式错误");
            }
            //学院名字
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            String ainfoName = row.getCell(2).getStringCellValue();
            AcademyInfo academyInfo = academyInfoService.findByAinfoName(ainfoName);
            if(academyInfo==null){
                throw new AdminException(998,"第"+(r+1)+"行"+ainfoName+"不存在");
            }
            major.setAinfoName(academyInfo.getAinfoName());
            major.setAinfoId(academyInfo.getAinfoId());

            //专业描述
            if(row.getPhysicalNumberOfCells()>3) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                major.setMajorDescription(row.getCell(3).getStringCellValue());
            }
            majorList.add(major);
        }
        for(Major major : majorList){
            create(major);
        }
    }
}
