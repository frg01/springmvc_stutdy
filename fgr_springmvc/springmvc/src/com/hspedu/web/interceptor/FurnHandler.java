package com.hspedu.web.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Controller
public class FurnHandler {

    @RequestMapping(value = "/hi")
    public String hi(){
        System.out.println("FurnHandler的hi()");
        return "login_ok";
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("FurnHandler的hello()");
        return "login_ok";
    }
}
