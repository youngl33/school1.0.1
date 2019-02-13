package com.school.service.impl;

import com.school.dtoObject.Subject;
import com.school.exception.AdminException;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectService;
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
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository repository;

    @Override
    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    @Override
    public Page<Subject> findBySubjectNameContainning(Pageable pageable, String subjectName) {
        return repository.findBySubjectNameContaining(pageable,subjectName);
    }

    @Override
    public Page<Subject> findByAinfoId(Pageable pageable, String ainfoId) {
        return repository.findByAinfoId(pageable,ainfoId);
    }

    @Override
    public Page<Subject> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Subject> findByAinfoIdAndSubjectNameContaining(Pageable pageable, String ainfoId, String subjectName) {
        return repository.findByAinfoIdAndSubjectNameContaining(pageable,ainfoId,subjectName);
    }

    @Override
    public Subject findOne(String subjectId) {
        return repository.findById(subjectId).orElse(null);
    }


    @Override
    public void delete(String subjectId) {
        repository.deleteById(subjectId);
    }

    @Override
    public Subject findBySubjectName(String subjectName) {
        return repository.findBySubjectName(subjectName);
    }

    @Transactional
    @Override
    public void importSubject(String fileName, MultipartFile file) throws Exception {
        Workbook wb=ExcelImportUtils.importFile(fileName,file);
        Sheet sheet=wb.getSheetAt(0);
        List<Subject> subjectList=new ArrayList<>();
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row=sheet.getRow(r);
            Subject subject=new Subject();
            if(row==null){
                continue;
            }

            //科目Id
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            subject.setSubjectId(row.getCell(0).getStringCellValue());
            if(StringUtils.isEmpty(subject.getSubjectId())){
                throw new AdminException(1,"表第"+(r+1)+"行科目Id格式错误！");
            }

            //科目名
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            subject.setSubjectName(row.getCell(1).getStringCellValue());
            if(StringUtils.isEmpty(subject.getSubjectName())){
                throw new AdminException(1,"表第"+(r+1)+"行科目名格式错误！");
            }

            //学院Id
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            subject.setAinfoId(row.getCell(2).getStringCellValue());
            if(StringUtils.isEmpty(subject.getAinfoId())){
                throw new AdminException(1,"表第"+(r+1)+"行学院Id格式错误");
            }
            subjectList.add(subject);
        }
        for(Subject subject:subjectList){
            save(subject);
        }
    }
}
