package com.school.controllerAPI;

import com.school.dtoObject.AcademyInfo;
import com.school.dtoObject.Class;
import com.school.dtoObject.Major;
import com.school.service.AcademyInfoService;
import com.school.service.ClassService;
import com.school.service.MajorService;
import com.school.utils.ResultVOUtils;
import com.school.vo.AcademyVO;
import com.school.vo.ClassVO;
import com.school.vo.MajorVO;
import com.school.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/academy")
public class AcademyControllerAPI {

    @Autowired
    private AcademyInfoService academyInfoService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ClassService classService;

    @GetMapping("/index")
    public ResultVO index(){
        return ResultVOUtils.fail(1,"123");
    }

    @GetMapping("/list")
    public ResultVO getList(){
        List<AcademyInfo> academyInfoList = academyInfoService.findAll();
        List<AcademyVO> academyVOList = new ArrayList<AcademyVO>();
        for(AcademyInfo academyInfo : academyInfoList){
            AcademyVO academyVO = new AcademyVO();
            //将学院添加到json中
            BeanUtils.copyProperties(academyInfo,academyVO);
            //查找学院下的专业
            List<MajorVO> majorVOList = new ArrayList<MajorVO>();
            List<Major> majorList = majorService.findByAinfoId(academyInfo.getAinfoId());
            for(Major major : majorList){
                MajorVO majorVO = new MajorVO();
                BeanUtils.copyProperties(major,majorVO);
                //查找专业下的所有班级
                List<ClassVO> classVOList = new ArrayList<ClassVO>();
                List<Class> classList = classService.findByMajorId(major.getMajorId());
                for(Class cla : classList){
                    ClassVO classVO = new ClassVO();
                    BeanUtils.copyProperties(cla,classVO);
                    classVOList.add(classVO);
                }
                if(classList.size()!=0){
                    majorVO.setClasses(classVOList);
                }
                majorVOList.add(majorVO);
            }
            academyVO.setMajors(majorVOList);
            academyVOList.add(academyVO);
        }
        return ResultVOUtils.success(academyVOList);
    }
}
