package com.hspedu.web.exeception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Controller
public class MyExceptionHandler {


    /**
     * 1.localException 方法处理局部异常
     * 2.处理ArithmeticException,NullPointerException异常
     * Exception ex:生成的异常对象 会传递给ex，通过ex可以获得相关信息 可以加入自己的业务逻辑
     * @return
     */
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String localException(Exception ex, HttpServletRequest request){
        System.out.println("异常信息是-" +ex.getMessage());

        //如何将异常的信息带到下一个页面
        request.setAttribute("reason", ex.getMessage());
        return "login_ok";
    }

    //编写方法，模拟异常,算数异常
    /**
     * 如果不做异常处理，由tomcat默认页面显示
     * @param num
     * @return
     */
    @RequestMapping(value = "/testException01")
    public String test01(Integer num){
        int i = 9/num;
        System.out.println("异常信息是" );
        return "my_view";
    }

    @RequestMapping(value = "/testGlobal")
    public String global(){
        //模拟一个异常 NumberFormatException
        //该异常没有在局部异常处理 交给全局异常处理
        int num = Integer.parseInt("hello");
        System.out.println("异常信息是" );
        return "my_view";
    }

    //抛出自定义异常
    @RequestMapping("/testException02")
    public String test02(){
       throw new AgeException("年龄必须在 1-120");
    }

    //数组越界异常
    @RequestMapping("/testException03")
    public String test03(){
        int[] arr = new int[]{3,9,10,190};
        //抛出数组越界的异常ArrayIndexOutOfBoundsException
        System.out.println(arr[90]);
        return "my_view";
    }

    @RequestMapping("/testException04")
    public String test04(){
        String str = "hello";
        //会抛出StringOutOfBoundsException
        char c = str.charAt(10);
        return "my_view";
    }
}
