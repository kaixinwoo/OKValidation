package org.ok.validation.builder.object;


import org.ok.validation.OKValidation;
import org.ok.validation.annotation.*;
import org.ok.validation.exception.OKValidationException;
import org.ok.validation.exception.UnSupportDataTypeException;
import org.ok.validation.unit.*;
import org.ok.validation.util.OKObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象类型验证
 */
public class OKObjectValidation {
    /**
     * 输入对象
     */
    protected Object input;
    /**
     * 统一错误码
     */
    protected String errCode;
    /**
     * 统一错误信息
     */
    protected String errMsg;
    /**
     * 规则验证列表
     */
    protected List<OKValidation> validationList = new ArrayList();
    /**
     * 排除验证字段
     * 考虑到同一个对象在不同的场景验证规则可能不同
     * 增加排除验证字段
     */
    protected Map<String, Integer> excludeMap = new HashMap<>();
    /**
     * 是否完成初始化
     */
    protected boolean isInit = false;

    public static OKObjectValidation builder() {
        return new OKObjectValidation();
    }

    /**
     * 设置输入对象
     * @param input 输入对象
     */
    public OKObjectValidation input(Object input) {
        this.input = input;
        return this;
    }

    /**
     * 设置默认错误码，当注解上没有设置errCode时，使用默认错误码
     * @param errCode 错误码
     */
    public OKObjectValidation errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    /**
     * 设置默认错误信息，当注解上没有设置errMsg时，使用默认错误信息
     * @param errMsg
     */
    public OKObjectValidation errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    /**
     * 设置排除验证字段
     * @param excludes 被排除的字段
     */
    public OKObjectValidation excludes(String... excludes) {
        if (excludes == null) {
            return this;
        }
        for (String exclude : excludes) {
            excludeMap.put(exclude, 1);
        }
        return this;
    }

    /**
     * 初始化
     */
    protected void init() {
        Object input = getInput();
        if (input == null) {
            throw new NullPointerException("无效的input");
        }
        Class clazz = input.getClass();
        Method[] methods = clazz.getMethods();
        Map<String, Integer> methodMap = new HashMap<>();
        for (Method method : methods) {
            methodMap.put(method.getName(), 1);
        }
        // 查找属性最多查找15层
        int index = 0;
        while (true) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (excludeMap.get(field.getName()) != null) {
                    continue;
                }
                // 获得变量上声明的注解
                Annotation[] annotations = field.getAnnotations();
                initValidation(field, annotations, methodMap);
            }
            clazz = clazz.getSuperclass();
            if (clazz == null) {
                break;
            }
            if (clazz.equals(Object.class)) {
                break;
            }
            if (index >= 15) {
                break;
            }
            index ++;
        }

        isInit = true;
    }

    /**
     * 初始化验证规则
     * @param field 要验证的字段
     * @param annotations 字段上标记的注解
     */
    protected void initValidation(Field field, Annotation[] annotations, Map<String, Integer> methodMap) {
        Class inputClass = input.getClass();
        String methodName = OKObjectUtil.getMethodName(field.getName());
        try {
            if (methodMap.get(methodName) == null) {
                throw new UnSupportDataTypeException(field.getName() + "没有get函数");
            }
            Method method = inputClass.getMethod(methodName);
            Object value = method.invoke(input);
            for (Annotation annotation : annotations) {
                OKValidation okValidation = getValidation(field, annotation, value);
                if (okValidation != null) {
                    validationList.add(okValidation);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取验证规则
     * @param field 被验证的字段
     * @param annotation 字段上的注解
     * @param value get函数获取到的数据
     * @return 返回验证对象
     */
    protected OKValidation getValidation(Field field, Annotation annotation, Object value) {
        OKValidation result = null;
        if (annotation instanceof Email) {
            Email email = (Email) annotation;
            String errCode = ("".equals(email.errCode()) || email.errCode() == null) ? this.errCode : email.errCode();
            String errMsg  = ("".equals(email.errMsg())  || email.errMsg()  == null) ? this.errMsg  : email.errMsg();
            if (value instanceof CharSequence == false) {
                throw new UnSupportDataTypeException("Email需要字符串类型 fieldName:" + field.getName());
            }
            CharSequence cs = (CharSequence) value;
            result = new EmailValidation(cs, errCode, errMsg);
        } else if (annotation instanceof NotNull){
            NotNull notNull = (NotNull) annotation;
            String errCode = ("".equals(notNull.errCode()) || notNull.errCode() == null) ? this.errCode : notNull.errCode();
            String errMsg  = ("".equals(notNull.errMsg())  || notNull.errMsg()  == null) ? this.errMsg  : notNull.errMsg();
            result = new NotNullValidation(value, errCode, errMsg);
        } else if (annotation instanceof NotEmpty) {
            NotEmpty notEmpty = (NotEmpty) annotation;
            String errCode = ("".equals(notEmpty.errCode()) || notEmpty.errCode() == null) ? this.errCode : notEmpty.errCode();
            String errMsg  = ("".equals(notEmpty.errMsg())  || notEmpty.errMsg()  == null) ? this.errMsg  : notEmpty.errMsg();
            result = new NotEmptyValidation(value, errCode, errMsg);
        } else if (annotation instanceof Ipv4) {
            Ipv4 ipv4 = (Ipv4) annotation;
            String errCode = ("".equals(ipv4.errCode()) || ipv4.errCode() == null) ? this.errCode : ipv4.errCode();
            String errMsg  = ("".equals(ipv4.errMsg())  || ipv4.errMsg()  == null) ? this.errMsg  : ipv4.errMsg();
            if (value instanceof CharSequence == false) {
                throw new UnSupportDataTypeException("Ipv4需要字符串类型 fieldName:" + field.getName());
            }
            CharSequence cs = (CharSequence) value;
            result = new IPv4Validation(cs, errCode, errMsg);
        } else if (annotation instanceof Mobile) {
            Mobile mobile = (Mobile) annotation;
            String errCode = ("".equals(mobile.errCode()) || mobile.errCode() == null) ? this.errCode : mobile.errCode();
            String errMsg  = ("".equals(mobile.errMsg())  || mobile.errMsg()  == null) ? this.errMsg  : mobile.errMsg();
            if (value instanceof CharSequence == false) {
                throw new UnSupportDataTypeException("Mobile需要字符串类型 fieldName:" + field.getName());
            }
            CharSequence cs = (CharSequence) value;
            result = new MobileValidation(cs, errCode, errMsg);
        } else if (annotation instanceof Regex) {
            Regex regex = (Regex) annotation;
            String errCode = ("".equals(regex.errCode()) || regex.errCode() == null) ? this.errCode : regex.errCode();
            String errMsg  = ("".equals(regex.errMsg())  || regex.errMsg()  == null) ? this.errMsg  : regex.errMsg();
            if (value instanceof CharSequence == false) {
                throw new UnSupportDataTypeException("Regex需要字符串类型 fieldName:" + field.getName());
            }
            CharSequence cs = (CharSequence) value;
            result = new StringRegexValidation(cs, errCode, errMsg, regex.regex());
        } else if (annotation instanceof RequireNumber) {
            RequireNumber requireNumber = (RequireNumber) annotation;
            String errCode = ("".equals(requireNumber.errCode()) || requireNumber.errCode() == null) ? this.errCode : requireNumber.errCode();
            String errMsg  = ("".equals(requireNumber.errMsg())  || requireNumber.errMsg()  == null) ? this.errMsg  : requireNumber.errMsg();
            if (value instanceof CharSequence == false) {
                throw new UnSupportDataTypeException("RequireNumber需要字符串类型 fieldName:" + field.getName());
            }
            CharSequence cs = (CharSequence) value;
            result = new RequiredNumberValidation(cs, errCode, errMsg);
        }
        return result;
    }

    /**
     * 获取输入值
     * @return 输入的对象
     */
    protected Object getInput() {
        return input;
    }

    /**
     * 验证
     * @throws OKValidationException 验证失败返回异常
     */
    public void validation() throws OKValidationException {
        if (isInit == false) {
            init();
        }
        for (OKValidation okValidation : validationList) {
            okValidation.validation();
        }
    }


}
