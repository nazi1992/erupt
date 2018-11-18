package com.example.demo.annoation;

/**
 * Created by Administrator on 2018/11/18 0018.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**(
 * 添加一些自己的注解
 * 该注解主要是标识推荐的类或写法
 */
//注解的目标,一般定义注解类，会有如下两个注解说明，
@Target(ElementType.ANNOTATION_TYPE.TYPE)
@Retention(RetentionPolicy.SOURCE)//注解存在的范围，该注解表示在编译期存在，因为该类只是为了标识而已
public @interface Recommend {
    String value() default "";
}
