package com.cloud.core.common.util;

import com.cloud.core.common.baseInterface.ResultEnumService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public final class EnumUtil {

    /**
     * 获取枚举类中对应的code
     * @param en  ob
     * @return code
     */
    public static String getCode(ResultEnumService en) {
        return getCodeForGeneric(en,"getCode");
    }
    /**
     * 获取枚举类中对应的code
     * @param en  ob
     * @return code
     */
    public static String getMessage(ResultEnumService en) {
        return getCodeForGeneric(en,"getMessage");
    }
    /**
     * 获取枚举类中对应的code
     *
     * @param en obj
     * @return code
     */
/*    public static String invokeCode(Object en) {
        if (ResultEnumService.class.isAssignableFrom(en.getClass()) && en instanceof Enum) {
            return getCodeForGeneric(en,"getCode");
        } else {
            log.warn("当前参数未实现: {} 类型或不是一个枚举类. ", ResultEnumService.class.getName());
            return null;
        }
    }*/

    private static String getCodeForGeneric(Object en,String method) {
        try {
            return (String) en.getClass().getMethod(method).invoke(en);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format("当前: {%s} 对象中不存在 [getCode()] 方法", en.getClass().getName()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("获取code失败!");
            return null;
        }
    }


}