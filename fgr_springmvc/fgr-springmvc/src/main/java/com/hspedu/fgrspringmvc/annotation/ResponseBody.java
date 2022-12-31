package com.hspedu.fgrspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Target({ElementType.METHOD})//能写在方法上就能写在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
    String value() default "";
}
