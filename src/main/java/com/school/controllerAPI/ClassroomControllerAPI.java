package com.school.controllerAPI;

import com.school.dtoObject.Building;
import com.school.dtoObject.Classroom;
import com.school.dtoObject.Location;
import com.school.service.BuildingService;
import com.school.service.ClassroomService;
import com.school.service.EmptyClassroomService;
import com.school.service.LocationService;
import com.school.utils.ResultVOUtils;
import com.school.vo.BuildingVO;
import com.school.vo.ClassroomVO;
import com.school.vo.LocationVO;
import com.school.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/classroom")
@RestController
public class ClassroomControllerAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EmptyClassroomService emptyClassroomService;

    @GetMapping("/emptyroom")
    public ResultVO emptyroom(@RequestParam(value = "courseBegin")Integer courseBegin,
                              @RequestParam(value="courseEnd") Integer courseEnd,
                              @RequestParam(value="coursedtlDay") Integer coursedtlDay,
                              @RequestParam(value="coursedtlSequence") Integer coursedtlSequence,
                              Model model){
        List<Location> locationList=locationService.findAll();
        List<LocationVO> locationVOList=new ArrayList<LocationVO>();
        for(Location location:locationList){
            LocationVO locationVO=new LocationVO();
            //将校区添加到json中
            BeanUtils.copyProperties(location,locationVO);
            //查找校区包含的教学楼
            List<Building> buildingList=buildingService.findByLocationId(locationVO.getLocationId());
            List<BuildingVO> buildingVOList=new ArrayList<BuildingVO>();
            for(Building building:buildingList){
                BuildingVO buildingVO=new BuildingVO();
                //将教学楼添加到json中
                BeanUtils.copyProperties(building,buildingVO);
                List<ClassroomVO> classroomVOList=new ArrayList<ClassroomVO>();
                List<Classroom> classroomList=emptyClassroomService.findEmptyClassroom(buildingVO.getBuildingId(),courseBegin,courseEnd,coursedtlDay,coursedtlSequence);
                for(Classroom classroom:classroomList){
                    ClassroomVO classroomVO=new ClassroomVO();
                    BeanUtils.copyProperties(classroom,classroomVO);
                    classroomVOList.add(classroomVO);
                }
                buildingVO.setClassrooms(classroomVOList);
                buildingVOList.add(buildingVO);
            }
            locationVO.setBuildings(buildingVOList);
            locationVOList.add(locationVO);
        }
        return ResultVOUtils.success(locationVOList);

    }

}
