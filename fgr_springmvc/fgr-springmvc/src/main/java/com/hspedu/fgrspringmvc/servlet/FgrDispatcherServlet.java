package com.hspedu.fgrspringmvc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hspedu.fgrspringmvc.annotation.Controller;
import com.hspedu.fgrspringmvc.annotation.RequestMapping;
import com.hspedu.fgrspringmvc.annotation.RequestParam;
import com.hspedu.fgrspringmvc.annotation.ResponseBody;
import com.hspedu.fgrspringmvc.context.FgrWebApplicationContext;
import com.hspedu.fgrspringmvc.handler.FgrHandler;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * FgrDispatcherServlet 充当原生DispatcherServlet 本质是一个Servlet 需要继承HttpServlet
 */
public class FgrDispatcherServlet extends HttpServlet {

    //定义属性 handlerList 保存FgrHandler【url和controller方法的映射关系】
    private List<FgrHandler> handlerList = new ArrayList<>();
    //定义属性 fgrWebApplicationContext 自己的spring容器
    FgrWebApplicationContext fgrWebApplicationContext = null;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取到web.xml中的contextConfigLocation
        String configLocation = servletConfig.getInitParameter("contextConfigLocation");
        //在init()方法中创建自己的spring容器，并初始化
        fgrWebApplicationContext = new FgrWebApplicationContext(configLocation);
        fgrWebApplicationContext.init();
        //完成url和Controller方法的映射
        initHandlerMapping();
        System.out.println("handlerList初始化的结果=" + handlerList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("FgrDispatcherServlet 的 doPost");
        //调用方法，完成分发请求
        executeDispatch(req,resp);
    }

    //编写方法，完成url和控制器方法的映射
    public void initHandlerMapping(){
        if (fgrWebApplicationContext.ioc.isEmpty()){
            return;
        }
        //比哪里ioc容器的bean对象，进行url映射处理
        for (Map.Entry<String,Object> entry :fgrWebApplicationContext.ioc.entrySet()){
            //取出Object的clazz对象
            Class<?> clazz = entry.getValue().getClass();
            //如果注入的bean对象是Controller 就去出它所有方法
            if (clazz.isAnnotationPresent(Controller.class)){
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    //判断该方法是否有@RequestMapping
                    if (declaredMethod.isAnnotationPresent(RequestMapping.class)){
                        //取出@RequestMapping的映射路径
                        RequestMapping requestMappingAnnotation = declaredMethod.getAnnotation(RequestMapping.class);
                        // 拼接 可以把工程路径 + url （getServletContext().getContextPath()）获取工程路径
                        //或也可以直接将tomcat的application context直接配置为 /
                        String url = requestMappingAnnotation.value();
                        //创建FgrHandler对象  是一个映射关系
                        FgrHandler fgrHandler = new FgrHandler(url, entry.getValue(), declaredMethod);
                        //放入handlerList
                        handlerList.add(fgrHandler);
                    }
                }
            }
        }
    }
    //编写方法，通过request对象，返回FgrHandler 如果没有返回404
    private FgrHandler getFgrHandler(HttpServletRequest request){
        //1.先获取用户请求的uri 得到的uri和保存的url 有一个工程路径问题
        String requestURI = request.getRequestURI();
        for (FgrHandler fgrHandler : handlerList) {
            if (requestURI.equals(fgrHandler.getUrl())){
                return fgrHandler;
            }
        }
        return null;
    }

    //编写方法，完成分发请求
    private void executeDispatch(HttpServletRequest request,HttpServletResponse response){
        FgrHandler fgrHandler = getFgrHandler(request);
        try {
            if (fgrHandler == null){//用户请求的资源不存在
                response.getWriter().print("<h1>404 Not Found Such Page</h1>");
            }else{
                //目标将HttpServletRequest和HttpServletResponse封装到参数数组
                //1.得到目标方法的所有形参信息
                Class<?>[] parameterTypes
                        = fgrHandler.getMethod().getParameterTypes();
                //2.创建参数数组[对应实参数组]，反射调用目标方法时使用
                Object[] params
                        = new Object[parameterTypes.length];
                //遍历形参数组，根据形参数组信息，将实参填充到实参数组中
                for (int i = 0; i< parameterTypes.length;i++){
                    //取出每一个形参类型
                    Class<?> parameterType = parameterTypes[i];
                    //如果形参是HttpServletRequest 就将request填充到params
                    //原生的springmvc  按类型匹配
                    if ("HttpServletRequest".equals(parameterType.getSimpleName())){
                        params[i] = request;
                    }else if ("HttpServletResponse".equals(parameterType.getSimpleName())){
                        params[i] = response;
                    }
                }
                //将http请求参数封装到param数组中，注意填充实参时的顺序问题
                /**
                 * 这种是针对方法里有HttpServletRequest和HttpServletResponse形参的
                 * 将需要传递给目标方法的 实参=>封装到参数数组 =》然后以反射调用的方式调用
                 */

                //1.获取http请求的参数集合String[]
                //处理提交的数据
                request.setCharacterEncoding("utf-8");
                Map<String, String[]> parameterMap = request.getParameterMap();
                //遍历parameterMap 将请求参数按照顺序填充到实参数组params
                for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
                    String name = entry.getKey();
                    //这里只考虑提交的参数是单值的情况 做一个简化 （多值是进行字符串处理）
                    String value = entry.getValue()[0];

                    //得到请求的参数对应目标方法的相应位置形参，进行填充
                    //专门写方法得到对应形参
                    int indexRequestParameterIndex =
                            getIndexRequestParameterIndex(fgrHandler.getMethod(), name);

                    if (-1 != indexRequestParameterIndex){//找到对应位置
                        params[indexRequestParameterIndex] = value;
                    }else{//并没有找到@RequestParam注解对应的参数 使用默认的机制进行匹配
                        //先得到目标方法的所有形参的名称 -专门编写一个方法
                        //得到的目标的形参名进行遍历 如果匹配就把请求的参数值，填充到params
                        List<String> parameterNames = getParameterNames(fgrHandler.getMethod());
                        for (int i = 0 ;i < parameterNames.size();i++){
                            //如果请求参数名和目标方法的形参名说明匹配成功
                            if (name.equals(parameterNames.get(i))){
                                params[i] = value;//填充到实参数组
                                break;
                            }
                        }

                    }
                }
                //反射调用目标方法
                Object result = fgrHandler.getMethod()
                        .invoke(fgrHandler.getController(), params);
                //这里对返回的结果解析 简化(视图解析器)
                if (result instanceof String){
                 String viewName = (String)result;
                 if (viewName.contains(":")){//说明返回的String结果forward:/login_ok.jsp或Redirect
                     String viewType = viewName.split(":")[0];
                     String viewPage = viewName.split(":")[1];//跳转的页面
                     if ("forward".equals(viewType)){//请求转发
                         System.out.println("工程路径" +request.getContextPath());
                         request.getRequestDispatcher(viewPage).forward(request,response);
                     }else if("redirect".equals(viewType)){//重定向
                         response.sendRedirect(viewPage);
                     }
                 }else{//默认请求转发
                     request.getRequestDispatcher(viewName).forward(request,response);
                 }
                }//json
                else if (result instanceof ArrayList){//如果是ArrayList
                    //目标方法是否有ResponseBody
                    Method method = fgrHandler.getMethod();
                    if (method.isAnnotationPresent(ResponseBody.class)){
                        //转json  使用Gson  或者 jackson下的工具类
                        ObjectMapper objectMapper = new ObjectMapper();
                        String resultJson =
                                objectMapper.writeValueAsString(result);
                        response.setContentType("text/html;charset=utf-8");
                        //直接返回
                        PrintWriter writer = response.getWriter();
                        writer.write(resultJson);
                        writer.flush();
                        writer.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //编写方法，返回请求参数是目标方法的第几个形参
    /**
     * @param method 目标方法
     * @param name 请求的参数名
     * @return 目标方法的第几个形参
     */
    public int getIndexRequestParameterIndex(Method method,String name){

        Parameter[] parameters = method.getParameters();
        for (int i = 0;i < parameters.length;i++) {
            //取出当前的参数
            Parameter parameter = parameters[i];
            //判断是不是有@RequestParam注解
            if (parameter.isAnnotationPresent(RequestParam.class)){//有这个注解
                //取出当前RequestParam的value值
                RequestParam requestParamAnnotation = parameter.getAnnotation(RequestParam.class);
                String value = requestParamAnnotation.value();
                //匹配的比较
                if (name.equals(value)){
                    return i;//找到请求参数，对应目标方法的形参位置
                }
            }
        }
        //没有匹配成功
        return -1;
    }

//    返回目标方法的所有形参的名称,并放入到集合中返回
    public List<String> getParameterNames(Method method){
        List<String> parameterList = new ArrayList<>();
        //获取到所有参数名称  默认情况下 parameter.getName()返回的名字不是形参真正的名字[arg0，arg1...]
        //需引用一个插件 使用java8特性
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            parameterList.add(parameter.getName());

        }
        return parameterList;
    }

}
