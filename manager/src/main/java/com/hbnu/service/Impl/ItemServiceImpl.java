package com.hbnu.service.Impl;

import com.hbnu.dao.ItemDescMapper;
import com.hbnu.dao.ItemMapper;
import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @Description
 * @author:lcy
 * @date 2022-05-05 11:07
 * @return
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUITable findItemByPage(Integer page, Integer rows) {

//        获取商品总数
        int total = itemMapper.selectCount(null);

//        获取每页的数据
        /*
         *获取第一页数据
         * select * from tb_item Limit 0, 20
         * 获取第二页数据
         * select * from tb_item Limit 20, 20
         * 获取第N页
         * select * from tb_item Limit (n-1) * 0, 20
         */
        int start = (page - 1) * rows;
        List<Item> itemList = itemMapper.findItemByPage(start, rows);

        EasyUITable easyUITable = new EasyUITable();
        easyUITable.setTotal(total);
        easyUITable.setRows(itemList);
        return easyUITable;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //添加事物操作
    public void saveItem(Item item, ItemDesc itemDesc) {
        //商品信息入库
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        //自动增长的主键id只有在入库之后才会有id值
        //mybatis特性：数据库在入库操作之后会自动回填id
        itemMapper.insert(item);

        //商品描述信息入库
        itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public void updateItem(Item item,ItemDesc itemDesc) {

        item.setCreated(new Date());
        itemMapper.updateById(item);

        itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
        itemDescMapper.updateById(itemDesc);

    }

    @Override
    public void deleteItem(Long[] ids) {
        itemMapper.deleteBatchIds(Arrays.asList(ids));

        //批量删除商品描述
        itemDescMapper.deleteBatchIds(Arrays.asList(ids));

    }

    @Override
    public void updateItemStatus(Long[] ids, int status) {

        // update tb_item set status = #{status}, updated = #{date} where id in (ids....)
        for (Long id:ids){
            Item item =new Item();
            item.setId(id).setStatus(status).setUpdated(new Date());
            itemMapper.updateById(item);
        }
    }

    @Override
    public ItemDesc findItemDescByItemId(Long itemId) {
        ItemDesc itemDesc = itemDescMapper.selectById(itemId);
        return itemDesc;
    }
}
