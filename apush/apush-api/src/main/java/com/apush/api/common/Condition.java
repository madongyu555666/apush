package com.apush.api.common;

import java.util.Map;
import java.util.function.Predicate;


/**
 * Predicate断言，其中的test的返回值时boolean，非常适合判断
 * @author madongyu-ds
 *
 */
public interface Condition extends Predicate<Map<String, Object>>{

}
