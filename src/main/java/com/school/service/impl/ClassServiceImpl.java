package com.school.service.impl;

import com.school.dtoObject.Class;
import com.school.dtoObject.Major;
import com.school.dtoObject.Student;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.repository.ClassRepository;
import com.school.service.ClassService;
import com.school.service.MajorService;
import com.school.service.StudentService;
import com.school.utils.ExcelImportUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Override
    public Class create(Class classdto) {
        return classRepository.save(classdto);
    }

    @Override
    public Class find(String classId) {
        return classRepository.findById(classId).orElse(null);
    }

    @Override
    public List<Class> findByMajorId(String majorId) {
        return classRepository.findByMajorId(majorId);
    }

    @Override
    public Page<Class> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void delete(String classId) {
        Class cla = classRepository.findById(classId).orElse(null);
        if(cla==null){
            throw new AdminException(ResultEnum.CLASS_NOE_EXIST);
        }
        Page<Student> studentList = studentService.findByClassId(PageRequest.of(0,100),cla.getClassId());
        for(Student student:studentList){
            studentService.delete(student.getStudentId());
        }
    }

    @Transactional
    @Override
    public void importClasses(String fileName, MultipartFile file) throws Exception {
        Workbook wb = ExcelImportUtils.importFile(fileName,file);
        Sheet sheet = wb.getSheetAt(0);
        List<Class> classList = new ArrayList<Class>();
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            Class cla = new Class();
            if(row==null){
                continue;
            }
            //班级Id
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            cla.setClassId(row.getCell(0).getStringCellValue());
            if(StringUtils.isEmpty(cla.getClassId())){
                throw new AdminException(999,"第"+(r+1)+"行班级号格式错误");
            }
            //专业Id
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            cla.setMajorId(row.getCell(1).getStringCellValue());
            if(StringUtils.isEmpty(cla.getMajorId())){
                throw new AdminException(999,"第"+(r+1)+"行专业ID格式错误");
            }
            Major major = majorService.findById(cla.getMajorId());
            if(major==null){
                throw new AdminException(998,cla.getMajorId()+"不存在");
            }
            //专业名
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            cla.setMajorName(row.getCell(2).getStringCellValue());
            if(StringUtils.isEmpty(cla.getMajorName())){
                throw new AdminException(999,"第"+(r+1)+"行专业名称格式错误");
            }
            //学院名
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            cla.setAinfoName(row.getCell(3).getStringCellValue());
            if(StringUtils.isEmpty(cla.getAinfoName())){
                throw new AdminException(999,"第"+(r+1)+"行学院名称格式错误");
            }
            //年级
            row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
            cla.setClassGrade((int)row.getCell(4).getNumericCellValue());
            if(cla.getClassGrade()==null){
                throw new AdminException(999,"第"+(r+1)+"行年级格式错误");
            }
            //班级人数
            row.getCell(5).setCellType(Cell.CELL_TYPE_NUMERIC);
            cla.setClassNum((int)row.getCell(5).getNumericCellValue());
            if(cla.getClassNum()==null){
                throw new AdminException(999,"第"+(r+1)+"行班级人数格式错误");
            }
            //教师名称
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            cla.setTeacherName(row.getCell(3).getStringCellValue());
            if(StringUtils.isEmpty(cla.getTeacherName())){
                throw new AdminException(999,"第"+(r+1)+"行教师名称格式错误");
            }
            classList.add(cla);
        }
        for(Class cla : classList){
            Class result = create(cla);
            if(result==null){
                throw new AdminException(ResultEnum.UNKNOW_ERROR);
            }
        }
    }
}
