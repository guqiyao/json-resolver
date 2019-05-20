package io.github.guqiyao;

import java.lang.annotation.*;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/4/10 16:22
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParameter {

    /**
     * 参数名
     *
     * @return  参数名
     */
    String value() default "";

    /**
     * 是否必须
     * @return  是否必须有这个参数
     */
    boolean required() default true;
}