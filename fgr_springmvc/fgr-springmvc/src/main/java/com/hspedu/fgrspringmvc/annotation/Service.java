package com.hspedu.fgrspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 注解 用于标识一个Service对象并注入到spring容器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
