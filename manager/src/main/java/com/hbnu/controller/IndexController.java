package com.hbnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @param
 * @Description 通用页面跳转
 * @author:xzq
 * @date 2022-05-05 10:17
 * @return
 */
@Controller
public class IndexController {

    @RequestMapping("/page/{pageName}")
    public String page(@PathVariable String pageName){
        return pageName;
    }
}
