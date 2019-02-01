package com.school.service.impl;

import com.school.dtoObject.Classroom;
import com.school.dtoObject.Course;
import com.school.dtoObject.CourseDetail;
import com.school.service.ClassroomService;
import com.school.service.CourseDetailService;
import com.school.service.CourseService;
import com.school.service.EmptyClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class EmptyClassroomServiceImpl implements EmptyClassroomService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private CourseDetailService courseDetailService;

    @Override
    public List<Classroom> findEmptyClassroom(Integer courseBegin,Integer courseEnd,Integer coursedtlDay,Integer coursedtlSequence) {

        List<Classroom> classroomList=classroomService.findAllList();
        List<Classroom> classroomList1 = new ArrayList<Classroom>();
        List<Classroom> classroomList2 = new ArrayList<Classroom>();
        classroomList1.addAll(classroomList);
        List<CourseDetail> courseDetailList= new ArrayList<CourseDetail>();
        List<Course> course=new ArrayList<Course>();
        List<Course> courseList1 = courseService.findByCourseBeginGreaterThanEqualAndCourseBeginLessThanEqual(courseBegin,courseEnd);
        List<Course> courseList2=courseService.findByCourseEndGreaterThanEqualAndCourseEndLessThanEqual(courseBegin,courseEnd);
        List<Course> courseList3=courseService.findByCourseBeginLessThanEqualAndCourseEndGreaterThanEqual(courseBegin,courseEnd);
        course.addAll(courseList1);
        course.addAll(courseList2);
        course.addAll(courseList3);
        for(Classroom classroom:classroomList1){
            boolean t =true;
             if(course.size()!=0){
                 for(Course course1:course){
                     courseDetailList=courseDetailService.findByCourseId(course1.getCourseId());
                     for(CourseDetail courseDetail:courseDetailList){
                         if(StringUtils.equals(classroom.getClassroomId(),courseDetail.getClassroomId())){
                             if(StringUtils.equals(courseDetail.getCoursedtlDay(),coursedtlDay)&&StringUtils.equals(courseDetail.getCoursedtlSequence(),coursedtlSequence)){
                                 t=false;
                                 break;
                             }
                         }
                     }
                     if(t==false){
                         break;
                     }
                 }
             }
            if(t){
                classroomList2.add(classroom);}
        }

        return classroomList2;
    }
}
