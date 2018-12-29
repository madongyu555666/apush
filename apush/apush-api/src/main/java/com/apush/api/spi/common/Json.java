package com.apush.api.spi.common;

/**
 * 把消息转换成json，或者把json转换为对象
 * @author madongyu-ds
 *
 */
public interface Json {
	    Json JSON = JsonFactory.create();

	    <T> T fromJson(String json, Class<T> clazz);

	    String toJson(Object json);
}
