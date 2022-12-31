package com.hspedu.web.requestParam;

import com.hspedu.web.requestParam.bean.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
//@RequestMapping("/vote")
//@Controller(value = "voteHandlerSelf")//有冲突先拿掉
public class VoteHandler {



    /**
     * @RequestParam(value = "name",required = false) 使得请求的参数和方法的形参可以名字不一致
    value = "name"表示提交的参数名是name  required = false表示可以无参数 默认是true表示必须有
     */
    @RequestMapping(value = "/vote01")
    public String test01(@RequestParam(value = "name",required = false) String username){
        System.out.println("得到的username= " + username);
        return "login_ok";
    }


    @RequestMapping(value = "/vote02")
    public String test02(@RequestHeader ("Accept-Encoding") String ae,
                         @RequestHeader("Host") String host){
        System.out.println("得到的Accept-Encoding= " + ae + "  Host=" + host);
        return "login_ok";
    }

  /**
     * 演示如果获取到提交数据->封装成java对象
     *
     * @return 老师说明
     * 1. 方法的形参用对应的类型来指定即可, SpringMVC会自动的进行封装
     * 2. 如果自动的完成封装, 要求提交的数据，参数名和对象的字段名保持一致
     * 3. 如果属性是对象，这里就是仍然是通过 字段名.字段名 比如Master [pet]
     * , 即提交的数据 参数名 是 pet.id pet.name， 这就是级联操作
     * 4. 如果提交的数据 的参数名和对象的字段名不匹配，则对象的属性值就是null
     * 5. 小伙伴疑惑，怎么就封装成功[底层仍然是反射+注解..]
     */
    @RequestMapping(value = "/vote03")
    public String test03(Master master){
        System.out.println("主人信息= " + master);
        return "login_ok";
    }


    /**
     * 使用servlet获取提交的数据
     * @return
     */
    @RequestMapping(value = "/vote04")
    public String test04(HttpServletRequest request,
                         HttpServletResponse response){
        String name = request.getParameter("name");
        System.out.println("name" + name);

        return "login_ok";
    }

    /**
     * 演示将提交的数据封装到java对象  springmvc会自动放入request域
     * 这样就可以在下面跳转的页面取出数据
     * @return
     */
    @RequestMapping(value = "/vote05")
    public String test05(Master master, HttpServletRequest request){
        //手动放入
        request.setAttribute("address","beijing");
        //修改master的值
        master.setName("alan");
        //request("master",master)  默认按照类名首字母小写进行存放
        return "login_ok";
    }

    /**
     * 演示Map<String,Object> 设置数据到request中
     * @return
     */
    @RequestMapping(value = "/vote06")
    public String test06(Master master, Map<String,Object> map){
        //通过map对象  添加属性到request中
        map.put("address","beijing...");
        return "login_ok";
    }


    /**
     *演示通过返回ModelAndView对象  将数据放入的request中
     * @return
     */
    @RequestMapping(value = "/vote07")
    public ModelAndView test07(Master master){
        //通过map对象  添加属性到request中
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("address","shanghai");
        //添加传入形参类的数据 ，会覆盖request域中的相同字段
        modelAndView.addObject("master","shanghai");
        modelAndView.setViewName("login_ok");
        return modelAndView;
    }


    /**
     *演示如何将数据设置到session中
     * @return
     */
    @RequestMapping(value = "/vote08")
    public String test08(Master master, HttpSession httpSession){
        //master对象默认放在request中
        //将master放入session中
        httpSession.setAttribute("master",master);
        httpSession.setAttribute("address","guangzhou");

        return "login_ok";
    }

    /**
     * 当Handler的方法被标识@ModelAttribute  视为前置方法
     * 类似前置方法，会切入到其他方法前执行【底层是AOP】
     */
    @ModelAttribute
    public void prepareModel(){
        System.out.println("prepareModel()----完成准备工作---");
    }
}



