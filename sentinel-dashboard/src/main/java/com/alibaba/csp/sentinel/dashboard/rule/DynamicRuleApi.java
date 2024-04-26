package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
public interface DynamicRuleApi<T extends RuleEntity> extends DynamicRuleProvider<List<T>>, DynamicRulePublisher<List<T>> {

    @SuppressWarnings("unchecked")
    default Class<T> ruleClass() {
        // 获取泛型类型T的Class对象
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        if (superclass == null) {
            throw new IllegalArgumentException("Generic superclass not parameterized");
        }
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        if (actualTypeArguments.length > 0) {
            return (Class<T>) actualTypeArguments[0];
        }
        throw new IllegalArgumentException("Generic superclass not parameterized");
    }
}
