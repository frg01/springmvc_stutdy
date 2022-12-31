package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 使用springmvc 类上标识@Controller 被视为一个控制器 注入容器
 */
@Controller
public class UserServlet {

//    编写方法响应用户请求

    /**
     * login用于响应登录请求
     * @RequestMapping(value = "/login")  类似原生Servlet配置的url-pattern
     * @return "login ok" 表示返回结果给视图解析器(InternalResourceViewResolver)
     * 视图解析器根据配置决定跳转到哪个页面
     */
    @RequestMapping(value = "/login")
    public String login() {
        System.out.println("login  ok");
        return "login_ok";
    }
}
