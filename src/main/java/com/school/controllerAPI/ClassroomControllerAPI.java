package com.school.controllerAPI;

import com.school.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/classromm")
@RestController
public class ClassroomControllerAPI {

    @Autowired
    private ClassroomService classroomService;


}
