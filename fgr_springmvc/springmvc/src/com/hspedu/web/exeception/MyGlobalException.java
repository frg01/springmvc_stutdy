package com.hspedu.web.exeception;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: guorui fu
 * @versiion: 1.0
 *标注@ControllerAdvice  是一个全局异常处理类
 */
@ControllerAdvice
public class MyGlobalException {

    /**
     *全局异常 Handler抛出的异常都可以捕获
     * 处理的全局异常NumberFormatException，ClassCastException
     *Exception ex 接收抛出的异常对象
     * @return
     */
    @ExceptionHandler({NumberFormatException.class,ClassCastException.class,AgeException.class})
    public String globalException(Exception ex, HttpServletRequest request){
        System.out.println("全局异常处理" + ex.getMessage());
        request.setAttribute("reason",ex.getMessage());
        return "login_ok";
    }
}
