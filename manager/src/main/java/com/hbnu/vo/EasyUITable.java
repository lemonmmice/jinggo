package com.hbnu.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @param
 * @Description
 * @author:xzq
 * @date 2022-05-05 11:09
 * @return
 */
@Data
@Accessors(chain = true)
public class EasyUITable {
    private Integer total;
    private List<?> rows;
}
