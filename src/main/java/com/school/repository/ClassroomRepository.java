package com.school.repository;

import com.school.dtoObject.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {

    List<Classroom> findByBuildingId(Integer buildingId);

    Page<Classroom> findByClassroomLocationAndBuildingId(Pageable pageable,String classroomLocation, Integer buildingId);

    Classroom findByClassroomLocationAndBuildingIdAndClassroomNo(String classroomLocation,Integer buildingId,String classroomNo);

    Page<Classroom> findByClassroomLocationAndBuildingIdAndClassroomType(Pageable pageable,String classroomLocation,Integer buildingId,String classroomType);

    Page<Classroom> findByClassroomLocation(Pageable pageable ,String classroomLocation);

    Page<Classroom> findByClassroomType(Pageable pageable,String classroomType);

    Page<Classroom> findByClassroomLocationAndClassroomType(Pageable pageable,String classroomLocation,String classroomType);

    Page<Classroom> findByBuildingId(Pageable pageable,Integer buildingId);

    Page<Classroom> findByBuildingIdAndClassroomType(Pageable pageable,Integer buildingId,String classroomType);
}
