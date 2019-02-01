package com.school.service.impl;

import com.school.dtoObject.Tax;
import com.school.exception.AdminException;
import com.school.repository.TaxRepository;
import com.school.service.TaxService;
import com.school.utils.ExcelImportUtils;
import com.school.utils.KeyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository repository;
/*
    @Override
    public Tax save(Tax tax) {
        return repository.save(tax);
    }

    @Override
    public Page<Tax> findByScheduleSemester(Pageable pageable, String scheduleSemester) {
        return repository.findByScheduleSemester(pageable, scheduleSemester);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndSubjectNameContaining(Pageable pageable, String scheduleSemester, String subjectName) {
        return repository.findByScheduleSemesterAndSubjectNameContaining(pageable, scheduleSemester, subjectName);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndTeacherName(Pageable pageable, String scheduleSemester, String teacherName) {
        return repository.findByScheduleSemesterAndTeacherName(pageable, scheduleSemester, teacherName);
    }

    @Override
    public Page<Tax> findByScheduleSemesterAndSubjectNameContainingAndTeacherName(Pageable pageable, String scheduleSemester, String subjectName, String teacherName) {
        return repository.findByScheduleSemesterAndSubjectNameContainingAndTeacherName(pageable, scheduleSemester, subjectName, teacherName);
    }

    @Override
    public Tax findOne(String taxId) {
        return repository.findById(taxId).orElse(null);
    }

    @Override
    public void delete(String taxId) {
        repository.deleteById(taxId);
    }

    //要么都导入要么都不导入
    @Transactional
    @Override
    public void importTax(String fileName, MultipartFile file) throws Exception {
        Workbook wb = ExcelImportUtils.importFile(fileName, file);
        Sheet sheet = wb.getSheetAt(0);
        List<Tax> taxList=new ArrayList<>();
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            Tax tax = new Tax();
            if (row == null) {
                continue;
            }

            //科目名
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            tax.setSubjectName(row.getCell(0).getStringCellValue());
            if (StringUtils.isEmpty(tax.getSubjectName())) {
                throw new AdminException(1, "表第" + (r + 1) + "行科目名格式错误！");
            }
            //科目Id
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            tax.setSubjectId(row.getCell(1).getStringCellValue());
            if (StringUtils.isEmpty(tax.getSubjectId())) {
                throw new AdminException(1, "表第" + (r + 1) + "行科目Id格式错误！");
            }
            //教师名
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            tax.setTeacherName(row.getCell(2).getStringCellValue());
            if (StringUtils.isEmpty(tax.getTeacherName())) {
                throw new AdminException(1, "表第" + (r + 1) + "行教师名格式错误！");
            }
            //教师id
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            tax.setTeacherId(row.getCell(3).getStringCellValue());
            if (StringUtils.isEmpty(tax.getTeacherId())) {
                throw new AdminException(1, "表第" + (r + 1) + "行教师Id格式错误！");
            }
            //学年信息
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            tax.setScheduleSemester(row.getCell(4).getStringCellValue());
            if (StringUtils.isEmpty(tax.getScheduleSemester())) {
                throw new AdminException(1, "表第" + (r + 1) + "行学年信息格式错误！");
            }
            //上课周
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            tax.setTaxWeek(row.getCell(5).getStringCellValue());
            if (StringUtils.isEmpty(tax.getTaxWeek())) {
                throw new AdminException(1, "表" + (r + 1) + "行上课周信息格式错误！");
            }
            //选课人数
            row.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);
            tax.setAvailableNum((int) row.getCell(6).getNumericCellValue());
            if (tax.getAvailableNum() == null) {
                throw new AdminException(1, "表" + (r + 1) + "行选课人数信息格式错误！");
            }
            //学时
            row.getCell(7).setCellType(Cell.CELL_TYPE_NUMERIC);
            tax.setTaxTime((int) row.getCell(7).getNumericCellValue());
            if (tax.getTaxTime() == null) {
                throw new AdminException(1, "表" + (r + 1) + "行学时信息格式错误！");
            }
            //学分
            row.getCell(8).setCellType(Cell.CELL_TYPE_NUMERIC);
            tax.setTaxScore(row.getCell(8).getNumericCellValue());
            if (tax.getTaxScore() == null) {
                throw new AdminException(1, "表" + (r + 1) + "行学分信息格式错误！");
            }

            tax.setTaxId(KeyUtils.uniqueKey());
            taxList.add(tax);

        }
        for(Tax tax:taxList){
            save(tax);
        }

    }*/
}
