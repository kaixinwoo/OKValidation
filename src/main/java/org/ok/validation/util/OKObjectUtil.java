package org.ok.validation.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OKObjectUtil {

    /**
     * 数组转换
     * @param input 输入 Object类型数组
     * @return Object类型数组
     */
    public static Object[] toArray(Object... input) {
        return input;
    }

    /**
     * 数组转list
     * @param input 输入 Object类型数组
     * @return 返回List
     */
    public static List toList(Object... input) {
        List result = new ArrayList();
        for (int i = 0; i < input.length; i++) {
            result.add(input[i]);
        }
        return result;
    }

    /**
     * 获取方法名
     * @param fieldName 根据输入对象成员变量的名字生成get函数方法名
     * @return 返回生成的方法名
     */
    public static String getMethodName(String fieldName) {
        String firstLetter = fieldName.substring(0, 1);
        String other = fieldName.substring(1);
        String methodName = "get" + firstLetter.toUpperCase() + other;
        return methodName;
    }

    /**
     * 获取目标值
     * @param input 输入对象
     * @param fieldName input对象的成员便令名称 如果输入的式基本数据类型（Number、CharSequence、Boolean）则此输入值无意义
     * @return 返回获取的数据
     * @throws InvocationTargetException 反射调用异常
     * @throws IllegalAccessException 无法访问呢异常
     * @throws NoSuchMethodException 为找到函数异常
     */
    public static Object getValue(Object input, String fieldName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object result;
        if (input == null) {
            result = null;
        } else {
            if (input instanceof CharSequence // 字符串
                    || input instanceof Number // 数字
                    || input instanceof Boolean) { // boolean
                result = input;
            } else if (input instanceof Map) { // map
                result = ((Map) input).get(fieldName);
            } else { // 其他类型的对象，反射get函数，获取值
                Class inputClass = input.getClass();
                String getMethodName = getMethodName(fieldName);
                Method method = inputClass.getMethod(getMethodName);
                result = method.invoke(input);
            }
        }
        return result;
    }

}
