package com.school.repository;

import com.school.dtoObject.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
   /* public List<Teacher> findByTeacherName(String TeacherName);*/
}
