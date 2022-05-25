package com.hbnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @param
 * @Description
 * @author:xzq
 * @date 2022-05-05 08:53
 * @return
 */
@Data
@Accessors(chain = true)
@TableName("tb_item")
public class Item extends BasePojo {
    @TableId(type = IdType.AUTO)
    private Long id; // 商品id
    private String title; // 商品标题
    @TableField("sell_point")
    private String sellPoint; // 商品卖点
    private Long price; // 商品价格
    private Integer num; // 商品库存
    private String barcode; // 商品条形码
    private String image; // 商品图片
    private Long cid; // 商品分类ID
    private Integer status; // 商品状态: 1-正常 2-下架 3-删除

}
