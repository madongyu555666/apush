package com.apush.tools;

import com.alibaba.fastjson.JSON;
import com.apush.api.Constants;
import com.apush.tools.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * json工具类
 * @author madongyu-ds
 *
 */
public final class Jsons {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jsons.class);

    /**
     * 把对象转为json格式的String
     * @param bean
     * @return
     */
    public static String toJson(Object bean) {
        try {
            return JSON.toJSONString(bean);
        } catch (Exception e) {
            LOGGER.error("Jsons.toJson ex, bean=" + bean, e);
        }
        return null;
    }

    /**
     * 把String格式的json转为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {

        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            LOGGER.error("Jsons.fromJson ex, json=" + json + ", clazz=" + clazz, e);
        }
        return null;
    }

    
    /**
     * 把byte格式的json转为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(byte[] json, Class<T> clazz) {
        return fromJson(new String(json, Constants.UTF_8), clazz);
    }
    

    /**
     * string转为多个对象
     * @param json
     * @param type
     * @return
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        try {
            return JSON.parseArray(json, type);
        } catch (Exception e) {
            LOGGER.error("Jsons.fromJsonToList ex, json=" + json + ", type=" + type, e);
        }
        return null;
    }

    /**
     * 通过类型和string转为对象
     * @param json
     * @param type
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        try {
            return JSON.parseObject(json, type);
        } catch (Exception e) {
            LOGGER.error("Jsons.fromJson ex, json=" + json + ", type=" + type, e);
        }
        return null;
    }

    /**
     * 判断是否是json格式
     * @param json
     * @return
     */
    public static boolean mayJson(String json) {
        if (Strings.isBlank(json))
            return false;
        if (json.charAt(0) == '{' && json.charAt(json.length() - 1) == '}')
            return true;
        if (json.charAt(0) == '[' && json.charAt(json.length() - 1) == ']')
            return true;
        return false;
    }

    /**
     * map转为string
     * @param map
     * @return
     */
    public static String toJson(Map<String, String> map) {
        if (map == null || map.isEmpty())
            return "{}";
        StringBuilder sb = new StringBuilder(64 * map.size());
        sb.append('{');
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            append(it.next(), sb);
        }
        while (it.hasNext()) {
            sb.append(',');
            append(it.next(), sb);
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * 把map拼接为Stringbuilder，长度可变
     * @param entry
     * @param sb
     */
    private static void append(Map.Entry<String, String> entry, StringBuilder sb) {
        String key = entry.getKey(), value = entry.getValue();
        if (value == null)
            value = Strings.EMPTY;
        sb.append('"').append(key).append('"');
        sb.append(':');
        sb.append('"').append(value).append('"');
    }
}
