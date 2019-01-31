package com.school.service.impl;

import com.school.dtoObject.Class;
import com.school.dtoObject.Student;
import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import com.school.repository.StudentRepository;
import com.school.service.ClassService;
import com.school.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassService classService;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public Page<Student> findByStudentNameContaining(Pageable pageable,String studentName) {
        return studentRepository.findByStudentNameContaining(pageable,studentName);
    }


    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    @Override
    public Page<Student> findByClassId(Pageable pageable,  String classID) {
        return studentRepository.findByClassId(pageable,classID);
    }

    @Transactional
    @Override
    public void importStudent(String fileName, MultipartFile file) throws Exception {
        //检测excel文件格式
        Workbook wb = ExcelImportUtils.importFile(fileName,file);
        for(int i=0;i<wb.getNumberOfSheets();i++){
            Sheet sheet = wb.getSheetAt(i);
            List<Student> studentList = new ArrayList<Student>();
            for(int r=1;r<=sheet.getLastRowNum();r++){
                Row row = sheet.getRow(r);
                Student student = new Student();
                if(row==null){
                    continue;
                }
                //学号
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentId(row.getCell(0).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentId())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行学号格式错误");
                }
                //密码
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentPassword(row.getCell(1).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentPassword())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行密码格式错误");
                }
                //姓名
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentName(row.getCell(2).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentName())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行姓名格式错误");
                }
                //性别
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentGender(row.getCell(3).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentGender())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行性别格式错误");
                }
                //联系方式
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentTel(row.getCell(4).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentTel())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行联系方式格式错误");
                }
                //地址
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentAddr(row.getCell(5).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentAddr())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行地址格式错误");
                }
                //出生日期
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentBorndate(DateFormatUtils.dateConverter2(row.getCell(6).getStringCellValue()));
                if(student.getStudentBorndate()==null){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行出生日期格式错误");
                }
                //头像
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentAvater(row.getCell(7).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentAvater())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行头像格式错误");
                }
                //身份证
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                student.setStudentIcard(row.getCell(8).getStringCellValue());
                if(StringUtils.isEmpty(student.getStudentIcard())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行身份证格式错误");
                }
                //班级
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                String classId = row.getCell(9).getStringCellValue();
                Class cla = classService.find(classId);
                if(cla==null){
                    throw new AdminException(999,classId+"班级不存在，请先添加该班级相关信息");
                }
                student.setClassId(classId);
                if(StringUtils.isEmpty(student.getClassId())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行班级格式错误");
                }
                //专业名字
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                student.setMajorName(row.getCell(10).getStringCellValue());
                if(StringUtils.isEmpty(student.getMajorName())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行专业名字格式错误");
                }
                //学院名字
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                student.setAinfoName(row.getCell(11).getStringCellValue());
                if(StringUtils.isEmpty(student.getAinfoName())){
                    throw new AdminException(999,"表"+(i+1)+"第"+(r+1)+"行学院名字格式错误");
                }
                //学生描述
                if(row.getPhysicalNumberOfCells()>12){
                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                    student.setStudentDescription(row.getCell(12).getStringCellValue());
                }
                studentList.add(student);
            }
            for(Student student: studentList){
                create(student);
            }
        }

    }

    @Override
    public void delete(String studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElse(null);
            studentRepository.delete(student);
        } catch (Exception e) {
            throw new AdminException(ResultEnum.STUDENT_CANTOT_DELETE);
        }
    }
}
