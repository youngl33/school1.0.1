package com.school.service.impl;

import com.school.dtoObject.Classroom;
import com.school.repository.ClassroomRepository;
import com.school.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
}
