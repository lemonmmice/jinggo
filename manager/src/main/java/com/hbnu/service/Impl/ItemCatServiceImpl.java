package com.hbnu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.dao.ItemCatMapper;
import com.hbnu.pojo.ItemCat;
import com.hbnu.service.ItemCatService;
import com.hbnu.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public String findItemCatById(Integer itemCatId) {

        ItemCat itemCat = itemCatMapper.selectById(itemCatId);

        return itemCat.getName();
    }

    /**
     * 根据 parentId查询一级目录
     * @param parentId
     * @return
     */

    @Override
    public List<EasyUITree> findEasyUITree(Integer parentId) {
        List<ItemCat> itemCatList  =findItemCatByParentId(parentId);

        List<EasyUITree>treeList =new ArrayList<EasyUITree>();
        for (ItemCat itemCat :itemCatList) {
            Long id=itemCat.getId();
            String text =itemCat.getName();
            //父级close ，子集open
            String state=itemCat.getIsParent() ? "closed" : "open";
            EasyUITree easyUITree=new EasyUITree(id,text,state);
            treeList.add(easyUITree);
        }
        return treeList;
    }

    private List<ItemCat> findItemCatByParentId(Integer parentId){
        //select * from tb_item_cat where parent_id ='parentId'
        QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<ItemCat>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList =itemCatMapper.selectList(queryWrapper);
        return itemCatList;
    }
}
