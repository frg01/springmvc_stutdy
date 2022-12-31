package com.hspedu.fgrspringmvc.context;

import com.hspedu.fgrspringmvc.annotation.AutoWired;
import com.hspedu.fgrspringmvc.annotation.Controller;
import com.hspedu.fgrspringmvc.annotation.Service;
import com.hspedu.fgrspringmvc.xml.XMLParser;
import javafx.scene.input.InputMethodTextRun;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 自己的spring容器
 */
public class FgrWebApplicationContext {
    //定义属性 保存扫描包及子包的类的全路径
    private List<String> classFullPathList = new ArrayList<>();
    //定义属性ioc，存放反射生成的Bean对象Controller,Service
    public ConcurrentHashMap<String, Object> ioc =
            new ConcurrentHashMap<>();

    //无参构造器
    public FgrWebApplicationContext() {
    }

    private String configLocation;//属性 标识spring容器配置文件

    public FgrWebApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }

    //编写方法，完成自己的spring容器的初始化
    public void init() {

//        String basePackage = XMLParser.getBasePackage("fgrspringmvc.xml");
        String basePackage = XMLParser.getBasePackage(configLocation.split(":")[1]);
        String[] basePackages = basePackage.split(",");
        if (basePackages.length > 0) {
            for (String pack : basePackages) {
                scanPackage(pack);
            }
        }
        System.out.println("classFullPathList=" + classFullPathList);
        //将扫描到的类反射到ioc容器
        executeInstance();
        System.out.println("扫描后ioc容器=" + ioc);

        //完成注入bean对象的属性的装配
        executeAutoWired();
        System.out.println("装配后 ioc容器=" + ioc);
    }


    //完成对包的扫描

    /**
     * @param pack 要扫描的包  ”com.hspedu.controller“
     */
    public void scanPackage(String pack) {
        //通过类的加载器得到pack包的工作路径【绝对路径】com.hspedu.controller --> D:\frg_springmvc\....com\hspedu\controller
        //细节：不要直接junil测试，否则url为null  先启动tomcat测试
        URL url = this.getClass().getClassLoader().getResource("/" + pack.replaceAll("\\.", "/"));
        //根据得到的绝对路径，对其进行扫描，把类的全路径，保存到classFullPathList

        String path = url.getFile();
//        System.out.println("path=" + path);
        //把目录也视为文件俩处理
        File dir = new File(path);
        //遍历dir【文件，子目录】
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {//如果是子目录，需要递归扫描
                scanPackage(pack + "." + f.getName());
            } else {
                //扫描到的文件可能是.class文件，也可能是其他文件 是.class文件也需要看有无注解，才能注入容器
                //先将类全路径 注入对象到容器时在处理
                String classFullPath =
                        pack + "." + f.getName().replaceAll(".class", "");

                classFullPathList.add(classFullPath);
            }
        }
    }

    //编写方法，将扫描到的类，子啊满足条件的情况下反射到ioc容器
    public void executeInstance() {
        //判断是否扫描到类
        if (classFullPathList.size() == 0) {
            return;
        }

        //遍历classFullPath进行反射
        try {
            for (String classFullPath : classFullPathList) {
                Class<?> clazz = Class.forName(classFullPath);
                //判断当前这个类有注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //得到类名首字母小写
                    String beanName = clazz.getSimpleName().substring(0, 1).toLowerCase()
                            + clazz.getSimpleName().substring(1);
                    ioc.put(beanName, clazz.newInstance());
                }//处理Service注解
                else if (clazz.isAnnotationPresent(Service.class)) {
                    //得到Service的value值  就是benaName
                    Service serviceAnnotation = clazz.getDeclaredAnnotation(Service.class);
                    String beanName = serviceAnnotation.value();
                    if ("".equals(beanName)) {//没有value 就使用默认的类名首字母小写作为beanName
                        if (clazz.getInterfaces() != null) {
                            //得到所有接口名称
                            Class<?>[] interfaces = clazz.getInterfaces();
                            Object instance = clazz.newInstance();
                            //遍历接口，通过多个接口名来注入
                            for (Class<?> anInterface : interfaces) {
                                String beanName2 =
                                        anInterface.getSimpleName().substring(0, 1).toLowerCase() + anInterface.getSimpleName().substring(1);
                                ioc.put(beanName2, instance);
                            }
                        }//3.(没有接口)使用类名的首字母小写来注入bean  通过clazz 来即可
                        else {
                            //直接使用类名注入ioc
                            String beanName3 = clazz.getSimpleName().substring(0, 1) + clazz.getSimpleName().substring(1);
                            ioc.put(beanName3, clazz.newInstance());
                        }
                    } else {//如果有指定名称 就使用该名称注入
                        ioc.put(beanName, clazz.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //编写方法，完成属性的自动装配
    public void executeAutoWired() {
        //判断有每有装配的对象
        if (ioc.isEmpty()) {
            return;//或抛出一个异常 throw new RuntimeException
        }
        //遍历ioc容器，判断是否需要装配  entry <String,Object>
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            String key = entry.getKey();
            Object bean = entry.getValue();
            //获取所有字段/属性
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //判断当前这个字段是否有AutoWired注解
                if (declaredField.isAnnotationPresent(AutoWired.class)) {
                    //得到当前这个字段的AutoWired值
                    AutoWired annotation = declaredField.getAnnotation(AutoWired.class);
                    String beanName = annotation.value();
                    if ("".equals(beanName)) {//没有设置value
                        //得到字段首字母小写，作为bean的名字
                        Class<?> type = declaredField.getType();
                        beanName = type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1);

                    }
                    //如果设置value 就按它装配
                    //从ioc容器中获取到bean
                    if (null == ioc.get(beanName)) {//指定的名字不再ioc中
                        throw new RuntimeException("ioc容器不存在要装配的bean");
                    }
                    //防止属性private 暴力破解
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(bean, ioc.get(beanName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
