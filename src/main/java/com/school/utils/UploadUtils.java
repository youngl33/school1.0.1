package com.school.utils;


import com.school.enums.ResultEnum;
import com.school.exception.AdminException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class UploadUtils {

    public static String uploadImg(MultipartFile file, String realPathLast) throws Exception{
        String fileName = file.getOriginalFilename();
        int suffixIndex = fileName.lastIndexOf(".")+1;
        String suffixName = fileName.substring(suffixIndex, fileName.length());
        String imgName = KeyUtils.uniqueKey()+"."+suffixName;
        FileOutputStream outputStream=null;
        InputStream sourceFile = file.getInputStream();
        String path ="";
        String realPath = "e:\\xampp\\htdocs\\img\\"+realPathLast+"\\";
        try {
            String imgRealPath = realPath + imgName;
            File temFile = new File(imgRealPath);
            if (!temFile.getParentFile().exists()) {
                temFile.mkdirs();
            }
            outputStream = new FileOutputStream(temFile);
            byte b[] = new byte[1024];
            int n;
            while ((n = sourceFile.read(b)) != -1) {
                outputStream.write(b, 0, n);
            }
            path="http://127.0.0.1/img/"+realPathLast+"/"+imgName;
        }catch (Exception e){
            throw new AdminException(ResultEnum.IMG_ERROR);
        }finally{
            sourceFile.close();
            outputStream.close();
        }
        return path;
    }
}
