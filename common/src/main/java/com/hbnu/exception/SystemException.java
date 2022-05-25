package com.hbnu.exception;

import com.hbnu.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lcy
 * @date
 */
@ControllerAdvice //处理控制器层发生的异常，底层实现通过aop实现
@Slf4j
public class SystemException {

    @ExceptionHandler(RuntimeException.class) //异常处理器,运行时处理异常
    public SysResult exception(Throwable throwable){
        throwable.printStackTrace();
        log.info(throwable.getMessage());//引入日志，将信息打印到日志里面
        return SysResult.fail("操作失败！！！");
    }

}
