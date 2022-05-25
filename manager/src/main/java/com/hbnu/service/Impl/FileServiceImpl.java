package com.hbnu.service.Impl;

import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author LCY
 * @date Created in 2022/5/12 11:09
 */
@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    //编写代码时，配置路径写在properties文件中
    @Value("${localDirPath}")
    private  String localDirPath;
    @Value("${urlDirPath}")
    private String urlDirPath;
//    //生产本地目录
//    private String localDirth =
//     "E"+ File.separator+ "DingLi"+File.separator+"class1911"+ File.separator+"jgImages"+ File.separator;
//    private String urlDirPath="http://image.jg.com/";

    /**\
     * 思路：
     *      1.校验用户上传的文件  jpg，jpeg，png，gif
     *      2.木马病毒.exe.jpg，校验用户上传的伪装文件
     *      3.分文件存储图片 yyyy/MM/dd/
     *      4.如何定义用户上传的文件名 uuid
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //获取用户上传的文件名

        String filename =uploadFile.getOriginalFilename();

        filename = filename.toLowerCase();//全部转换成小写
        //使用正则表达式校验用户上传的文件类型
        if (filename.matches("^.+\\.(jpg|png|jpeg|gif)$")) {
           return ImageVO.fail();
        }
        //获取图片输入流。
        try{
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width =bufferedImage.getWidth();
            int height =bufferedImage.getHeight();
            if (width == 0 || height ==0){
                return ImageVO.fail();
            }
            System.out.println("用户上传的是图片。。。。。。。。");

        //生成日期目录
        String dateDirPath=new SimpleDateFormat("yyyy"+File.separator+"MM"+File.separator+"dd"+File.separator).format(new Date());
        //生成真实目录
        String realDirPath= localDirPath +dateDirPath;
        //生成真实文件
        File realDirFile =new File(realDirPath);
        if (!realDirFile.exists()){
            realDirFile.mkdirs();
        }
        //自动生成随机文件名
        String uuidName = UUID.randomUUID().toString();
        //生成文件类型
        String fileType=filename.substring(filename.lastIndexOf(""));
        //文件真实路径
        String realFilename =realDirPath+uuidName+fileType;

        //上传文件
        uploadFile.transferTo(new File(realFilename));

        //图片回显,url:图片的虚拟路径
           // String url="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=0&pn=0&spn=0&di=7077213605308923905&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=860853266%2C1174871120&os=1565685654%2C2212323501&simid=860853266%2C1174871120&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1113%2F052420110515%2F200524110515-2-1200.jpg%26refer%3Dhttp%3A%2F%2Fimg.jj20.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1655018123%26t%3Dd4386d244fd6e06b31c372e208edb0ae&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B33da_z%26e3Bv54AzdH3FkzAzdH3Fz6u2AzdH3Fxfx3AzdH3Fd9camm_d_z%26e3Bip4s&gsm=1&islist=&querylist=&dyTabStr=MCwzLDYsMSw0LDIsNSw3LDgsOQ%3D%3D";
            String url=urlDirPath+dateDirPath+uuidName+fileType;
            return new ImageVO(0,url,width,height);
        }catch(Exception e){
            e.printStackTrace();
            return ImageVO.fail();
        }


    }
}
