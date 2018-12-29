package com.apush.common.condition;

import com.apush.api.common.Condition;

import java.util.Map;

/**
 * 总是通过的条件
 * @author madongyu-ds
 *
 */
public final class AwaysPassCondition implements Condition {
    public static final Condition I = new AwaysPassCondition();

    @Override
    public boolean test(Map<String, Object> env) {
        return true;
    }
}
