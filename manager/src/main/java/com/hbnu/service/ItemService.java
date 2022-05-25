package com.hbnu.service;

import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemDesc;
import com.hbnu.vo.EasyUITable;

/**
 * @param
 * @Description
 * @author:xzq
 * @date 2022-05-05 11:08
 * @return
 */
public interface ItemService {
    EasyUITable findItemByPage(Integer page, Integer rows);

    void saveItem(Item item, ItemDesc itemDesc);

    void updateItem(Item item,ItemDesc itemDesc);

    void deleteItem(Long[] ids);

    void updateItemStatus(Long[] ids, int status);

    ItemDesc findItemDescByItemId(Long itemId);
}
