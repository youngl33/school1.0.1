package com.school.service;

import com.school.dtoObject.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MajorService {

    /** 创建    */
    Major create(Major major);

    /** 通过Id查询       */
    Major findById(String majorId);

    /** 通过名字查询       */
    Major findByMajorName(String majorName);

    /** 查找学院下的专业       */
    List<Major> findByAinfoId(String ainfoId);

    /** 所有专业          */
    Page<Major> findAll(Pageable pageable);

    void importMajor(String fileName, MultipartFile file) throws Exception;

}
