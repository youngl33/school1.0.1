package com.school.repository;

import com.school.dtoObject.StudentCourse;
import com.school.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentCourseRepositoryTest {

    @Autowired
    private StudentCourseRepository repository;

    @Test
    public void create(){
        StudentCourse studentCourse=new StudentCourse();
        studentCourse.setScourseId(KeyUtils.uniqueKey());
        studentCourse.setStudentId("201620180732");
        studentCourse.setCourseId("1548811422423992751");
        studentCourse.setScheduleSemester("2018-2019学年第一学期");
        StudentCourse result=repository.save(studentCourse);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStudentId() {

        PageRequest request=new PageRequest(0,10);
        Page<StudentCourse> studentCoursePage=repository.findByStudentId(request,"201620180732");
        Assert.assertNotEquals(0,studentCoursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemester() {

        PageRequest request=new PageRequest(0,10);
        Page<StudentCourse> studentCoursePage=repository.findByScheduleSemester(request,"2018-2019学年第一学期");
        Assert.assertNotEquals(0,studentCoursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndStudentId() {

        PageRequest request=new PageRequest(0,10);
        Page<StudentCourse> studentCoursePage=repository.findByScheduleSemesterAndStudentId(request,"2018-2019学年第一学期","201620180732");
        Assert.assertNotEquals(0,studentCoursePage.getTotalElements());
    }

    @Test
    public void findByCourseId() {

        PageRequest request=new PageRequest(0,10);
        Page<StudentCourse> studentCoursePage=repository.findByCourseId(request,"1548811422423992751");
        Assert.assertNotEquals(0,studentCoursePage.getTotalElements());
    }

    @Test
    public void findByScheduleSemesterAndCourseId() {

        PageRequest request=new PageRequest(0,10);
        Page<StudentCourse> studentCoursePage=repository.findByScheduleSemesterAndCourseId(request,"2018-2019学年第一学期","1548811422423992751");
        Assert.assertNotEquals(0,studentCoursePage.getTotalElements());
    }
}