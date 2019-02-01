package com.school.repository;

import com.school.dtoObject.StudentCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,String> {
    Page<StudentCourse> findByStudentId(Pageable pageable, String studentId);

    Page<StudentCourse> findByScheduleSemester(Pageable pageable, String scheduleSemester);

    Page<StudentCourse> findByScheduleSemesterAndStudentId(Pageable pageable, String scheduleSemester, String studentId);

    Page<StudentCourse> findByCourseId(Pageable pageable,String CourseId);

    Page<StudentCourse> findByScheduleSemesterAndCourseId(Pageable pageable, String scheduleSemester, String courseId);
}
