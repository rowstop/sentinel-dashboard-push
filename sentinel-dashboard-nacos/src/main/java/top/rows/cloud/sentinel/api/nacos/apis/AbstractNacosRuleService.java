package top.rows.cloud.sentinel.api.nacos.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleApi;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import top.rows.cloud.sentinel.api.nacos.NacosProperties;

import java.util.Collections;
import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
public abstract class AbstractNacosRuleService<T extends RuleEntity> implements DynamicRuleApi<T> {

    protected final ConfigService configService;
    protected final NacosProperties nacosProperties;
    private final Class<T> ruleClass;

    protected AbstractNacosRuleService(ConfigService configService, NacosProperties nacosProperties) {
        this.configService = configService;
        this.nacosProperties = nacosProperties;
        this.ruleClass = ruleClass();
    }

    @Override
    public List<T> getRules(String app) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        String rules = configService.getConfig(app + dataIdSuffix(),
                nacosProperties.getGroupId(), 4000);
        if (StringUtil.isEmpty(rules)) {
            return Collections.emptyList();
        }
        return JSON.parseArray(rules, ruleClass);
    }

    @Override
    public void publish(String app, List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + dataIdSuffix(),
                nacosProperties.getGroupId(), JSON.toJSONString(rules));
    }

    protected abstract String dataIdSuffix();
}
