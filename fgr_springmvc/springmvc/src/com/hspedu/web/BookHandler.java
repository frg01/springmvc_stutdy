package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@RequestMapping("/user")
@Controller
public class BookHandler {
    //查询[GET]
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public String getBook(@PathVariable("id")String id){
        System.out.println("查询书籍 id=" + id);
        return "login_ok";
    }

    //查询[GET]  形参名与提交input的name保持一致
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addBook(String book){
        System.out.println("添加的书籍 book=" + book);
        return "login_ok";
    }

    //删除[delete]  形参名与提交input的name保持一致
    //redirect:/user/login_ok 重定向  这里的第一个/会被解析到后端的工程路径
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String id){
        System.out.println("删除的书籍 id=" + id);
        return "redirect:/user/login_ok";
    }
    //上面的方法重定向到这，去改变method
    @RequestMapping(value = "/login_ok")
    public String successGenecal(){
        return "login_ok";
    }


    //查询[GET]  形参名与提交input的name保持一致
    @PutMapping(value = "/update/{id}")
    public String updateBook(@PathVariable("id") String id){
        System.out.println("修改的书籍的书籍 id=" + id);
        return "redirect:/user/login_ok";
    }


}
