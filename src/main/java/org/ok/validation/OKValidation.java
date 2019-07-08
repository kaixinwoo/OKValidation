package org.ok.validation;

import org.ok.validation.exception.OKValidationException;

public interface OKValidation {

    /**
     * 验证
     * @throws OKValidationException 验证失败时抛出异常
     */
    void validation() throws OKValidationException;

    /**
     * <pre>
     * 设置验证数据是否可以为null，不包含NotNull验证规则
     * mayBeNull = true 输入数据为null，视为验证通过，如果输入数据不等于null则必须符合验证规则
     * mayBeNull = false 输入数据不能为null，为null验证失败
     * </pre>
     * @param bool 输入数据是否可以为null
     */
    void mayBeNull(boolean bool);
}
