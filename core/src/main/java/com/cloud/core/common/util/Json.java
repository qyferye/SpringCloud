package com.cloud.core.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Json {

    private static ObjectMapper om = new ObjectMapper();

    /**
     * json转对象
     *
     * @param str   欲转换字符串
     * @param clazz 转换的class类型
     * @param <T>   T
     * @return obj
     */
    public static <T> T readVal(String str, Class<T> clazz) {
        try {
            return om.readValue(str, clazz);
        } catch (IOException e) {
            log.error("JSON str ===>> obj ", e);
        }
        return null;
    }

    /**
     * json 转成list<clazz>
     *
     * @param str   欲转换字符串
     * @param clazz 转换的class类型
     * @param <T>   T
     * @return str
     */
    public static <T> List<T> readListVal( String str, Class<T> clazz) {
        try {
            return om.readValue(str, om.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("JSON str ===>> list<obj> ", e);
        }

        return null;
    }

    /**
     * 对象转json字符串
     *
     * @param o  Object
     * @return str
     */
    public static String writeObj(Object o) {
        try {
            return om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("JSON obj ===>> str ", e);
        }

        return null;
    }
}
