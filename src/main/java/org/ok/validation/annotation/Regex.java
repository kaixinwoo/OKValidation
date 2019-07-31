package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 正则表达式验证
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Regex {

    String errCode() default "";

    String errMsg() default "";

    // 输入的正则表达式
    String regex() default "";
}
