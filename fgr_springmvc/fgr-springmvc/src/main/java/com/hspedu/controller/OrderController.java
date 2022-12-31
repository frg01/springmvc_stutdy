package com.hspedu.controller;

import com.hspedu.fgrspringmvc.annotation.Controller;
import com.hspedu.fgrspringmvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Controller
public class OrderController {

    //编写方法,可以列出妖怪列表  设定两个参数
    @RequestMapping(value = "/order/list")
    public void listOrder(HttpServletRequest request, HttpServletResponse response){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("<h1>订单列表信息</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //编写方法,可以列出妖怪列表  设定两个参数
    @RequestMapping(value = "/order/add")
    public void addOrder(HttpServletRequest request, HttpServletResponse response){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("<h1>添加订单</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
