package com.school.repository;

import com.school.dtoObject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> findByTaxId(String taxId);

}
