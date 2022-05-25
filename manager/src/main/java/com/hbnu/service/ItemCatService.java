package com.hbnu.service;

import com.hbnu.vo.EasyUITree;

import java.util.List;

/**
 * @author
 * @date
 */
public interface ItemCatService {
    String findItemCatById(Integer itemCatId);

    List<EasyUITree> findEasyUITree(Integer parentId);
}
