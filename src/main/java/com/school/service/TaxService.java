package com.school.service;

import com.school.dtoObject.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxService {

    /**查找一个课程*/
    Tax findOne(String taxId);

    /**保存课信息*/
    Tax save(Tax tax);

    /**通过学年找课*/
    Page<Tax> findByScheduleSemester(Pageable pageable,String scheduleSemester);

    /**通过学年和模糊查找科目名找课*/
    Page<Tax> findByScheduleSemesterAndSubjectNameContaining(Pageable pageable,String scheduleSemester,String subjectName);

    /**通过学年和教师名找课*/
    Page<Tax> findByScheduleSemesterAndTeacherName(Pageable pageable,String scheduleSemester,String teacherName);

    /**通过学年和模糊查找科目名和教师名来找课*/
    Page<Tax> findByScheduleSemesterAndSubjectNameContainingAndTeacherName(Pageable pageable,String scheduleSemester,String subjectName,String teacherName);

}
