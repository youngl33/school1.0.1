package com.school.service.impl;

import com.school.dtoObject.Classroom;
import com.school.exception.AdminException;
import com.school.repository.ClassroomRepository;
import com.school.service.ClassroomService;
import com.school.utils.ExcelImportUtils;
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
public class ClassroomServiceImp implements ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    @Override
    public Page<Classroom> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Classroom save(Classroom classroom){
        return repository.save(classroom);
    }

    @Override
    public Page<Classroom> findByClassroomLocationAndBuildingId(Pageable pageable,String classroomLocation,Integer buildingId){
        return repository.findByClassroomLocationAndBuildingId(pageable,classroomLocation,buildingId);
    }

    @Override
    public Classroom findByClassroomLocationAndBuildingIdAndClassroomNo(String classroomLocation, Integer buildingId, String classroomNo) {
        return repository.findByClassroomLocationAndBuildingIdAndClassroomNo(classroomLocation,buildingId,classroomNo);
    }

    @Override
    public Page<Classroom> findByClassroomLocationAndBuildingIdAndClassroomType(Pageable pageable, String classroomLocation,Integer buildingId, String classroomType) {
        return repository.findByClassroomLocationAndBuildingIdAndClassroomType(pageable,classroomLocation,buildingId,classroomType);
    }

    @Override
    public Page<Classroom> findByClassroomLocation(Pageable pageable, String classroomLocation) {
        return repository.findByClassroomLocation(pageable,classroomLocation);
    }

    @Override
    public Classroom findOne(Integer classroomId) {
        return repository.findById(classroomId).orElse(null);
    }

    @Override
    public Page<Classroom> findByClassroomType(Pageable pageable, String classroomType) {
        return repository.findByClassroomType(pageable,classroomType);
    }

    @Override
    public Page<Classroom> findByClassroomLocationAndClassroomType(Pageable pageable, String classroomLocation, String classroomType) {
        return repository.findByClassroomLocationAndClassroomType(pageable,classroomLocation,classroomType);
    }

    @Override
    public Page<Classroom> findByBuildingId(Pageable pageable, Integer buildingId) {
        return repository.findByBuildingId(pageable,buildingId);
    }

    @Override
    public Page<Classroom> findByBuildingIdAndClassroomType(Pageable pageable, Integer buildingId, String classroomType) {
        return repository.findByBuildingIdAndClassroomType(pageable,buildingId,classroomType);
    }

    @Override
    public List<Classroom> findAllList() {
        return repository.findAll();
    }

    @Override
    public Page<Classroom> search(Pageable pageable, String classroomLocation, Integer buildingId, String classroomType) {
        Page<Classroom> classroomPage = null;
        if (!StringUtils.isEmpty(classroomLocation)) {
            if (buildingId == 0 && !StringUtils.isEmpty(classroomType)) {
                classroomPage = repository.findByClassroomLocationAndClassroomType(pageable, classroomLocation, classroomType);
            } else if (buildingId != 0 && StringUtils.isEmpty(classroomType)) {
                classroomPage = repository.findByClassroomLocationAndBuildingId(pageable, classroomLocation, buildingId);
            } else if (buildingId != 0 && !StringUtils.isEmpty(classroomType)) {
                classroomPage = repository.findByClassroomLocationAndBuildingIdAndClassroomType(pageable, classroomLocation, buildingId, classroomType);
            } else if (buildingId == 0 && StringUtils.isEmpty(classroomType)) {
                classroomPage = repository.findByClassroomLocation(pageable, classroomLocation);
            }
        } else if (buildingId == 0 && !StringUtils.isEmpty(classroomType)) {
            classroomPage = repository.findByClassroomType(pageable, classroomType);
        } else if (buildingId != 0 && StringUtils.isEmpty(classroomType)) {
            classroomPage = repository.findByBuildingId(pageable, buildingId);
        } else if (buildingId != 0 && !StringUtils.isEmpty(classroomType)) {
            classroomPage=repository.findByBuildingIdAndClassroomType(pageable, buildingId, classroomType);
        } else if (buildingId == 0 && StringUtils.isEmpty(classroomType)) {
            classroomPage = repository.findAll(pageable);
        }
        return classroomPage;
    }

    @Override
    public List<Classroom> findByBuildingId(Integer buildingId) {
        return  repository.findByBuildingId(buildingId);
    }

    @Transactional
    @Override
    public void importClassroom(String fileName, MultipartFile file) throws Exception {
        Workbook wb=ExcelImportUtils.importFile(fileName,file);
        Sheet sheet=wb.getSheetAt(0);
        List<Classroom> classroomList=new ArrayList<>();
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row =sheet.getRow(r);
            Classroom classroom=new Classroom();
            if(row==null){
                continue;
            }
            //教室号
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            classroom.setClassroomNo(row.getCell(0).getStringCellValue());
            if(StringUtils.isEmpty(classroom.getClassroomNo())){
                throw new AdminException(1,"表第"+(r+1)+"行教室号格式错误！");

            }

            //教学楼Id
            row.getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
            classroom.setBuildingId((int)row.getCell(1).getNumericCellValue());
            if(classroom.getBuildingId()==null){
                throw new AdminException(1,"表第"+(r+1)+"行教学楼Id格式错误!");
            }

            //教室类型
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            classroom.setClassroomType(row.getCell(2).getStringCellValue());
            if(StringUtils.isEmpty(classroom.getClassroomType())){
                throw new AdminException(1,"表第"+(r+1)+"行教室类型格式错误！");
            }

            //座位数
            row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
            classroom.setClassroomSeats((int)row.getCell(3).getNumericCellValue());
            if(classroom.getClassroomSeats()==null){
                throw new AdminException(1,"表第"+(r+1)+"行座位数格式错误！");
            }

            //所在校区
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            classroom.setClassroomLocation(row.getCell(4).getStringCellValue());
            if(StringUtils.isEmpty(classroom.getClassroomLocation())){
                throw new AdminException(1,"表第"+(r+1)+"行校区格式错误！");
            }
            classroomList.add(classroom);
        }
        for(Classroom classroom:classroomList){
            save(classroom);
        }
    }
}
