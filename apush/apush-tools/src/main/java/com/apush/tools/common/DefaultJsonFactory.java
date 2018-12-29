package com.apush.tools.common;

import com.apush.api.spi.Spi;
import com.apush.api.spi.common.Json;
import com.apush.api.spi.common.JsonFactory;
import com.apush.tools.Jsons;

/**
 * 默认的json转换工厂，有spi注解
 * @author madongyu-ds
 *
 */
@Spi
public final class DefaultJsonFactory implements JsonFactory, Json {
    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return Jsons.fromJson(json, clazz);
    }

    @Override
    public String toJson(Object json) {
        return Jsons.toJson(json);
    }

    @Override
    public Json get() {
        return this;
    }
}