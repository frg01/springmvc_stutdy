package com.hspedu.fgrspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 用于标识是一个控制器组件
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
