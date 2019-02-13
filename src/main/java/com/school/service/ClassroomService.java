package com.school.service;

import com.school.dtoObject.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClassroomService {

    /**通过教学楼查找教室*/
    List<Classroom> findByBuildingId(Integer buildingId);

    /**查找某个教室的信息*/
    Classroom findOne(Integer classroomId);

    /**查找所有空教室信息*/
    List<Classroom> findAllList();

    /**查找所有教室信息*/
    Page<Classroom> findAll(Pageable pageable);

    /**保存一个教室的信息*/
    Classroom save(Classroom classroom);

    /**通过所在校区查找教室*/
    Page<Classroom> findByClassroomLocation(Pageable pageable,String classroomLocation);

    /**通过所在校区和教学楼查找教室*/
    Page<Classroom> findByClassroomLocationAndBuildingId(Pageable pageable,String classroomLocation,Integer buildingId);

    /**通过所在校区和教学楼和教室号查找教室*/
    Classroom findByClassroomLocationAndBuildingIdAndClassroomNo(String classroomLocation,Integer buildingId,String classroomNo);

    /**通过所在校区和教学楼和教室类型查找教室*/
    Page<Classroom> findByClassroomLocationAndBuildingIdAndClassroomType(Pageable pageable,String classroomLocation,Integer buildingId,String classroomType);

    /**通过教室类型查找教室信息*/
    Page<Classroom> findByClassroomType(Pageable pageable,String classroomType);

    /**通过所在校区和教室类型查找教室信息*/
    Page<Classroom> findByClassroomLocationAndClassroomType(Pageable pageable,String classroomLocation,String classroomType);

    /**通过教学楼Id查找教室*/
    Page<Classroom> findByBuildingId(Pageable pageable,Integer buildingId);

    /**通过教学楼和教师类型查找教室*/
    Page<Classroom> findByBuildingIdAndClassroomType(Pageable pageable,Integer buildingId,String classroomType);

    /**封装了一个查找功能*/
    Page<Classroom> search(Pageable pageable,String classroomLocation,Integer buildingId,String classroomType);

    /**上传教室*/
    void importClassroom(String fileName,MultipartFile file) throws Exception;

}
