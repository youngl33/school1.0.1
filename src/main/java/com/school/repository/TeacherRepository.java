package com.school.repository;

import com.school.dtoObject.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    public List<Teacher> findByTeacherName(String teacherName);

    public Page<Teacher> findByTeacherStatus(Pageable pageable,String teacherStatus);

    public Page<Teacher> findByTeacherPosition(Pageable pageable,String teacherPosition);

    public Page<Teacher> findByAinfoIdAndTeacherStatus(Pageable pageable, String ainfoId,String teacherStatus);

    public Page<Teacher> findByTeacherPositionAndTeacherStatus(Pageable pageable,String teacherPosition,String teacherStatus);

    public Page<Teacher> findByAinfoIdAndTeacherPositionAndTeacherStatus(Pageable pageable,String ainfoId,String teacherPosition,String teacherStatus);

}
