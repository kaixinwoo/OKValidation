package org.ok.validation;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public interface OKValidation {

    /**
     * 验证
     * @throws OKValidationException 验证失败时抛出异常
     */
    void validation() throws OKValidationException;

}
