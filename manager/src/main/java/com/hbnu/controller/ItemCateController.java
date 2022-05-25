package com.hbnu.controller;

import com.hbnu.service.ItemCatService;
import com.hbnu.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCateController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/queryItemName")
    public String findItemCatById(Integer itemCatId){

        return itemCatService.findItemCatById(itemCatId);
    }

    @RequestMapping("/list")
    public List<EasyUITree> findItemCatVyParentId(@RequestParam(value = "id",defaultValue ="0" ) Integer parentId){

        return itemCatService.findEasyUITree(parentId);
    }

}
