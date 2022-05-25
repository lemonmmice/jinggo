package com.hbnu.controller;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.SysResult;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @Description
 * @author:xzq
 * @date 2022-05-05 11:06
 * @return
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/query")
    public EasyUITable findItemByPage(Integer page, Integer rows){

        return itemService.findItemByPage(page,rows);
    }
    /**
     * 商品新增分析：
     *          请求地址：http：//localhost：8080/itrm/save
     *          请求参数：id=1&title=fhwoa
     *          响应结果：SysResult对象
     */
    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc){

            itemService.saveItem(item,itemDesc);//调用业务方法返回前端数据
            return SysResult.success();

    }
    @RequestMapping("/update")
    public SysResult updateItem(Item item,ItemDesc itemDesc){

        itemService.updateItem(item,itemDesc);
        return SysResult.success();


    }


    @RequestMapping("/delete")
    public SysResult deleteItem(Long[] ids){
        itemService.deleteItem(ids);
        return SysResult.success();
    }

    /**
     * 商品下架：
     *      请求url：http：//localhost：8080/itrm/instock
     *      请求参数：ids：1，2，3
     *      响应结果：sysresult对象
     */

    @RequestMapping("/instock")
     public SysResult instock(Long[] ids){

        int status =2;
        itemService.updateItemStatus(ids,status);
        return SysResult.success();
     }

     @RequestMapping("/reshelf")
    public SysResult reshelf(Long[] ids){
         int status =1;
         itemService.updateItemStatus(ids,status);
         return SysResult.success();
     }
    /**
     * 商品描述信息回显：
     *      请求地址：http：//localhost：8080/item/query/item/desc/1474392033\
     *      结果：SysResult对象
     */
    @RequestMapping("/query/item/desc/{itemId}")
    public SysResult findItemDescByItemId(@PathVariable Long itemId){
       ItemDesc itemDesc= itemService.findItemDescByItemId(itemId);
        return SysResult.success(itemDesc);
    }
}
