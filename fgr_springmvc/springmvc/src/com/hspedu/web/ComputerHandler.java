package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@RequestMapping(value = "/computer")
@Controller
public class ComputerHandler {

    @PostMapping(value = "/show")
    public String showComputer(String brand,String price,String num){
        System.out.println("电脑信息如下：\n" +
                "品牌：" + brand + "  价格:" + price + "  数量：" + num);
        return "login_ok";
    }
}
