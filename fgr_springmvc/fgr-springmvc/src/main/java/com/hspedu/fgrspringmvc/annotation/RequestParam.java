package com.hspedu.fgrspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 目标方法的参数上，标识http请求的参数
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
