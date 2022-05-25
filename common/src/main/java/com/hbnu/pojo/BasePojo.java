package com.hbnu.pojo;

import lombok.experimental.Accessors;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @Description
 * @author:xzq
 * @date 2022-05-05 08:54
 * @return
 */
@lombok.Data
@Accessors(chain = true)
public class BasePojo implements Serializable {
    private static final long serialVersionUID = 5466937469127555568L;

    private Date created;   //创建时间
    private Date updated;    //更新时间
}
