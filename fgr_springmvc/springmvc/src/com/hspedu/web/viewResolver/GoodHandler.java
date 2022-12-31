package com.hspedu.web.viewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@RequestMapping("/goods")
@Controller
public class GoodHandler {

    @RequestMapping("/buy")
    public String buy(){
        System.out.println("buy方法被调用");
        return "fgrView";
    }

    //演示直接指定请求转发或重定向的页面
    @RequestMapping(value = "/order")
    public String order(){
        System.out.println("=======order()======");
        //请求转发
//        return "forward:/WEB-INF/pages/my_view.jsp";
        //重定向  不能去/WEB-INF的内部目录，只有请求转发可以 因为服务器不允许浏览器直接访问服务器中的内部目录
        return "redirect:/login.jsp";
    }
}
