package com.hbnu.controller;

import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author LCY
 * @date Created in 2022/5/12 10:20
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file")
    /**
     *  fileImage：前端给的什么名字就用什么名字
     */
    public String file(MultipartFile fileImage) throws IOException {
        //获取上传的文件名
        String filename =fileImage.getOriginalFilename();


        //指定文件存储的位置
        String dirPath ="E:"+ File.separator+"DingLi"+File.separator+"class1911"+File.separator+"jG-images"+File.separator;
        File dirFile =new File(dirPath);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }

        //创建文件，用于接收用户上传的文件 E:\dingli\class1911\jg-images\img03.jpg
        String realFilename =dirPath+filename;
        File realFile = new File(realFilename);

        //上传文件
        fileImage.transferTo(realFile);

        return "文件上传成功！！";

    }
    /**
     * 图片上传分析 localhost:8080/pic/upload?dir=image
     * 请求参数：uploadFile
     * 响应结果：imageVO对象
     */
    @RequestMapping("/pic/upload")
    public ImageVO uploadFile(MultipartFile uploadFile){
        ImageVO imageVO=fileService.upload(uploadFile);

        return imageVO;

    }
}
