package com.hbnu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LCY
 * @date Created in 2022/5/17 14:58
 */
@RestController
public class ProtController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public String getPort(){
        return "本次用户访问的服务器端口"+port;
    }
}
