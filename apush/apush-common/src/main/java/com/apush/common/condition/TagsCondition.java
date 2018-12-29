package com.apush.common.condition;

import com.apush.api.common.Condition;

import java.util.Map;
import java.util.Set;

/**
 * 标签条件
 * @author madongyu-ds
 *
 */
public final class TagsCondition implements Condition {
    private final Set<String> tagList;

    public TagsCondition(Set<String> tags) {
        this.tagList = tags;
    }

    @Override
    public boolean test(Map<String, Object> env) {
        //2.按标签过滤,目前只有include, 后续会增加exclude
        String tags = (String) env.get("tags");
        return tags != null && tagList.stream().anyMatch(tags::contains);
    }
}
