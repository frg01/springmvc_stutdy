package com.hspedu.controller;

import com.hspedu.entity.Monster;
import com.hspedu.fgrspringmvc.annotation.*;
import com.hspedu.service.MonsterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Controller
public class MonsterController {

    @AutoWired
    private MonsterService monsterService ;

    //编写方法,可以列出妖怪列表  设定两个参数
    @RequestMapping(value = "/monster/list")
    public void listMonster(HttpServletRequest request, HttpServletResponse response){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");

        StringBuilder content = new StringBuilder("<h1>妖怪列表：</h1>");
        //调用monsterService
        List<Monster> monsters = monsterService.listMonster();
        content.append("<table border='1px' width='500px' style='border-collapse:collapse' >");
        for (Monster monster : monsters) {
            content.append("<tr><td>" + monster.getId() + "</td><td> " + monster.getName() +
                    "</td><td>" + monster.getAge() +"</td><td>" + monster.getSkill() + "</td></tr>");
        }
        content.append("</table>");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //增加方法  通过name返回对应的monster
    @RequestMapping(value = "/monster/find")
    public void findMonsterByName(HttpServletRequest request,
                                  HttpServletResponse response,
                                    @RequestParam( value= "name") String name){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");
        System.out.println("---接收到的name---");
        StringBuilder content = new StringBuilder("<h1>妖怪列表：</h1>");
        //调用monsterService
        List<Monster> monsters = monsterService.findMonsterByName(name);
        content.append("<table border='1px' width='400px' style='border-collapse:collapse' >");
        for (Monster monster : monsters) {
            content.append("<tr><td>" + monster.getId() + "</td><td> " + monster.getName() +
                    "</td><td>" + monster.getAge() +"</td><td>" + monster.getSkill() + "</td></tr>");
        }
        content.append("</table>");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理妖怪的登录 返回请求转发或重定向的字符串
    @RequestMapping("/monster/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String mName){
        //将mName设置到request域中
        request.setAttribute("mName",mName);
        System.out.println("接收到mName = " + mName);
        boolean b = monsterService.login(mName);
        if (b){//登录成功
            return "redirect:/login_ok.jsp";
        }else{//登录失败
            return "redirect:/login_error.jsp";
        }
    }

    //编写方法，返回json格式的数据 调用此方法的springmvc底层，结果返回至调用处
    @RequestMapping(value = "/monster/list/json")
    @ResponseBody(value = "json")
    public List<Monster> listMonsterByJson(HttpServletRequest request,
                                                HttpServletResponse response){
        List<Monster> monsters = monsterService.listMonster();
        return monsters;
    }
}
