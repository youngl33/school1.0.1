package com.school.repository;

import com.school.dtoObject.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Page<Teacher> findByTeacherNameContainingAndTeacherStatusAndAinfoId(Pageable pageable,String teacherName,String teacherStatus,String ainfoId);

    Page<Teacher> findByTeacherNameContainingAndTeacherStatus(Pageable pageable,String teacherName,String teacherStatus);

    List<Teacher> findByTeacherNameContaining(String teacherName);

    List<Teacher> findByTeacherName(String teacherName);

    Page<Teacher> findByTeacherStatus(Pageable pageable,String teacherStatus);

    Page<Teacher> findByTeacherPosition(Pageable pageable,String teacherPosition);

    Page<Teacher> findByAinfoIdAndTeacherStatus(Pageable pageable, String ainfoId,String teacherStatus);

    Page<Teacher> findByTeacherPositionAndTeacherStatus(Pageable pageable,String teacherPosition,String teacherStatus);

    Page<Teacher> findByAinfoIdAndTeacherPositionAndTeacherStatus(Pageable pageable,String ainfoId,String teacherPosition,String teacherStatus);

}
