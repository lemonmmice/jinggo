package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author LCY
 * @date Created in 2022/5/12 11:00
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVO {

    private Integer error;// 0:表示是图片；1：表示非图片
    private String url; //url表示图片的虚拟路径；
    private Integer width; //图片的宽度
    private Integer height;//图片的高度

    //指定失败的方法
    public static ImageVO fail(){
        return new ImageVO(1,null,null,null);
    }
}

