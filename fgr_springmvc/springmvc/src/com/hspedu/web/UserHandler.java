package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@RequestMapping(value = "/user")
@Controller//处理器 注入到容器
public class UserHandler {

    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    public String buy(){
        System.out.println("购买商品");
        return "login_ok";
    }

    /**
     * params = "bookId"请求该目标方法时 必须给bookId参数 参数无限定
     * search(String bookId)请求方法时携带参数bookId
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/find",params = "bookId",method = RequestMethod.GET)
    public String search(String bookId) {
        System.out.println("查询书籍 bookId=" + bookId);
        return "login_ok";
    }

    /**
     * 通配符 * 任意单层路径
     * 通配符 ** 任意多层路径
     * 通配符 ？？ 一个？代表一个路径中任意字符
     * value = "/message/**"  可以匹配message下的多层路径
     * @return
     */
    @RequestMapping(value = "/message/**")
    public String im(){
        System.out.println("发送消息");
        return "login_ok";
    }

    /**
     *前端/user/reg/jack/300
     * 会自动匹配 username -> jack   userId-> 300  称为路径变量
     * @PathVariable 给形参指定路径变量  形参名可随意指定 不会有影响
     */
    @RequestMapping(value = "/reg/{username}/{userId}")
    public String register(@PathVariable ("username")String name,@PathVariable("userId") String userId){
        System.out.println("接收到参数-- " + "username=" + name + "userId=" + userId);
        return "login_ok";
    }
}
