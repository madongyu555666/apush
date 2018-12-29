package com.apush.common.condition;

import com.apush.api.common.Condition;

import javax.script.*;
import java.util.Map;

/**
 * 脚本条件（状态）ScriptEngineManager实现字符串公式灵活计算，处理excel的公式
 * @author madongyu-ds
 *
 */
public final class ScriptCondition implements Condition {
    private static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static final ScriptEngine jsEngine = scriptEngineManager.getEngineByName("js");

    private final String script;

    public ScriptCondition(String script) {
        this.script = script;
    }

    @Override
    public boolean test(Map<String, Object> env) {
        try {
            return (Boolean) jsEngine.eval(script, new SimpleBindings(env));
        } catch (Exception e) {
            return false;
        }
    }
}
