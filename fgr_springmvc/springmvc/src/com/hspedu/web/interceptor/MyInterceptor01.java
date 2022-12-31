package com.hspedu.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class MyInterceptor01 implements HandlerInterceptor {

    /**
     * 该方法在目标方法执行前被执行
     * 如果preHandle（）返回false 不再继续执行目标方法
     * 可获取到request，response，handler
     * 根据业务可进行拦截 指定跳转
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor01--preHandle()---");
        //获取到用户提交的关键字
        String keyword = request.getParameter("keyword");
        if (keyword.contains("病毒")){
            request.getRequestDispatcher("/WEB-INF/pages/login_ok.jsp").forward(request,response);
            return false;
        }
        System.out.println("keyword=" + keyword);
        return true;//被拦截的目标方法不会执行，返回
    }

    /**
     * 目标方法执行之后会执行postHandle
     * 该方法可以获取到目标方法返回的ModelAndView
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor01---postHandle");
    }


    /**
     * 在视图渲染后被执行，进行资源处理工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor01---afterCompletion");
    }
}
