package com.hspedu.web.viewResolver;

import com.hspedu.web.viewResolver.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@RequestMapping("/login")
@Controller
public class LoginHandler {

    @RequestMapping("/do")
    public String doLogin(User user){
        System.out.println("注册的doogin()方法\n" + user.getName() + " " + user.getPwd() );

        if ("fgr".equals(user.getName()) && "123".equals(user.getPwd())){
            //成功页面
            return "forward:/WEB-INF/pages/login_ok.jsp";
        }else{
            return "redirect:/login_error.jsp";
        }
    }
}
