package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 正则表达式验证
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Regex {

    /**
     *
     * @return
     */
    String errCode() default "";

    /**
     *
     * @return
     */
    String errMsg() default "";

    /**
     * 输入的正则表达式
     * @return
     */
    String regex() default "";
}
