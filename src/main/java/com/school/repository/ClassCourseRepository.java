package com.school.repository;

import com.school.dtoObject.ClassCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassCourseRepository extends JpaRepository<ClassCourse,String> {
/*

    Page<ClassCourse> findByScheduleSemester(Pageable pageable,String scheduleSemester);

    Page<ClassCourse> findByScheduleSemesterAndClassId(Pageable pageable,String scheduleSemester,String classId);

    Page<ClassCourse> findByClassId(Pageable pageable,String classId);
*/

}
