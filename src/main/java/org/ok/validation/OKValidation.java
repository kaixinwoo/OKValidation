package org.ok.validation;

import org.ok.validation.exception.OKValidationException;

import java.lang.reflect.InvocationTargetException;

public interface OKValidation {

    /**
     * 验证
     * @param input
     * @throws OKValidationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    void validation(Object input) throws OKValidationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

}
