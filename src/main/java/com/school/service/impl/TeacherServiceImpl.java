package com.school.service.impl;
import com.school.dtoObject.Teacher;
import com.school.exception.AdminException;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherService;
import com.school.utils.DateFormatUtils;
import com.school.utils.ExcelImportUtils;
import org.apache.poi.ss.usermodel.*;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Override
    public Teacher save(Teacher teacher){
        return repository.save(teacher);
    }

    @Override
    public List<Teacher> findByTeacherName(String teacherName){
        return repository.findByTeacherName(teacherName);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Teacher> findByTeacherStatus(Pageable pageable, String  teacherStatus) {
        return repository.findByTeacherStatus(pageable, teacherStatus);
    }

    @Override
    public Page<Teacher> findByAcademyIdAndTeacherStatus(Pageable pageable,String ainfoId,String  teacherStatus){
        return repository.findByAinfoIdAndTeacherStatus(pageable,ainfoId,teacherStatus);
    }

    @Override
    public Page<Teacher> findByTeacherPosition(Pageable pageable, String teacherPosition) {
        return repository.findByTeacherPosition(pageable,teacherPosition);
    }

    @Override
    public Page<Teacher> findByTeacherPositionAndTeacherStatus(Pageable pageable, String teacherPositioin, String  teacherStatus) {
        return repository.findByTeacherPositionAndTeacherStatus(pageable,teacherPositioin,teacherStatus);
    }

    @Override
    public Page<Teacher> findByAinfoIdAndTeacherPositionAndTeacherStatus(Pageable pageable, String ainfoId, String teacherPosition,String  teacherStatus) {
        return repository.findByAinfoIdAndTeacherPositionAndTeacherStatus(pageable,ainfoId,teacherPosition,teacherStatus);
    }

    @Override
    public Teacher findOne(String teacherId){
        return repository.findById(teacherId).orElse(null);
    }

    @Override
    public List<Teacher> findByTeacherNameContaining(String teacherName) {
        return repository.findByTeacherNameContaining(teacherName);
    }

    @Override
    public  Page<Teacher> findByTeacherNameContainingAndTeacherStatus(Pageable pageable,String teacherName,String teacherStatus){
        return repository.findByTeacherNameContainingAndTeacherStatus(pageable,teacherName,teacherStatus);
    }

    @Override
    public Page<Teacher> findByTeacherNameContainingAndTeacherStatusAndAinfoId(Pageable pageable,String teacherName,String teacherStatus,String ainfoId){
        return repository.findByTeacherNameContainingAndTeacherStatusAndAinfoId(pageable,teacherName,teacherStatus,ainfoId);
    }

    @Transactional
    @Override
    public void importTeacher(String fileName, MultipartFile file) throws Exception {
        Workbook wb=ExcelImportUtils.importFile(fileName,file);
        Sheet sheet=wb.getSheetAt(0);
        List<Teacher> teacherList=new ArrayList<>();
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row=sheet.getRow(r);
            Teacher teacher=new Teacher();
            if(row==null){
                continue;
            }

            //教师Id
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherId(row.getCell(0).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherId())){
                throw new AdminException(1,"表第"+(r+1)+"行教师Id格式错误！");
            }

            //教师密码
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherPassword(row.getCell(1).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherPassword())){
                throw new AdminException(1,"表第"+(r+1)+"行教师密码格式错误！");
            }

            //教师名
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherName(row.getCell(2).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherName())){
                throw new AdminException(1,"表第"+(r+1)+"行教师名格式错误！");
            }

            //头像
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherAvater(row.getCell(3).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherAvater())) {
                throw new AdminException(1, "表第" +(r+1)+ "行教师头像格式错误！");
            }

            //电话
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherTel(row.getCell(4).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherTel())){
                throw new AdminException(1,"表第"+(r+1)+"行教师电话格式错误！");
            }

            //出生日期
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherBorndate(DateFormatUtils.dateConverter2(row.getCell(5).getStringCellValue()));
            if(teacher.getTeacherBorndate()==null){
                throw new AdminException(1,"表第"+(r+1)+"行教师出生日期格式错误！");
            }

            //教师性别
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherGender(row.getCell(6).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherGender())){
                throw new AdminException(1,"表第"+(r+1)+"行教师性别格式错误！");
            }

            //身份证号
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherIcard(row.getCell(7).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherIcard())){
                throw new AdminException(1,"表第"+(r+1)+"行教师身份证号格式错误！");
            }

            //住址
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherAddr(row.getCell(8).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherAddr())){
                throw new AdminException(1,"表第"+(r+1)+"行教师住址格式错误！");
            }

            //教师职位
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherPosition(row.getCell(9).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherPosition())){
                throw new AdminException(1,"表第"+(r+1)+"行教师职位格式错误！");
            }

            //教师状态
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setTeacherStatus(row.getCell(10).getStringCellValue());
            if(StringUtils.isEmpty(teacher.getTeacherStatus())){
                throw new AdminException(1,"表第"+(r+1)+"行教师状态格式错误！");
            }
            //教师所在学院
            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
            teacher.setAinfoId(row.getCell(11).getStringCellValue());

            teacherList.add(teacher);
        }
        for(Teacher teacher:teacherList){
            save(teacher);
        }
    }
}
