package org.ok.validation;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public interface OKValidation {

    /**
     * 验证
     * @throws OKValidationException
     */
    void validation() throws OKValidationException;

}
