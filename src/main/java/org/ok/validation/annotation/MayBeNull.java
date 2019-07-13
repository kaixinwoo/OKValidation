package org.ok.validation.annotation;

import java.lang.annotation.*;

/**
 * 要求被验证的数据可以为null
 * 如果被验证的数据为非null，要求被验证的数据必须要符合其他的验证逻辑
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MayBeNull {
}
