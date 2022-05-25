package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lcy
 * @date
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    private Integer status;//返回状状态码，200表示操作成功,201表示操作失败
    private String msg;//服务端返回给客户端的消息
    private Object data; //服务端返回给客户端的数据
    //成功返回状态
    public static SysResult success(){
        return new SysResult(200,null,null);
    }
    //成功返回状态，数据
    public static SysResult success(Object data){
        return new SysResult(200,null,data);
    }
    //成功返回状态，数据 ，消息
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }
    //失败返回状态
    public static SysResult fail(Object data){
        return new SysResult(201,null,null);
    }
    //失败返回状态，信息
    public static SysResult fail(String msg){
        return new SysResult(201,msg,null);
    }
}
