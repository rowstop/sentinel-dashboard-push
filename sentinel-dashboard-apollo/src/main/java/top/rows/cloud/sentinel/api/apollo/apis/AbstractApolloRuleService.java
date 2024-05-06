package top.rows.cloud.sentinel.api.apollo.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleApi;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;
import top.rows.cloud.sentinel.api.apollo.ApolloProperties;

import java.util.Collections;
import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
public abstract class AbstractApolloRuleService<T extends RuleEntity> implements DynamicRuleApi<T> {

    private static final String EDITOR = "sentinel-dashboard ";
    protected final ApolloOpenApiClient apiClient;
    protected final ApolloProperties properties;
    private final Class<T> ruleClass;


    public AbstractApolloRuleService(ApolloOpenApiClient apiClient, ApolloProperties properties) {
        this.apiClient = apiClient;
        this.properties = properties;
        this.ruleClass = ruleClass();
    }

    @Override
    public List<T> getRules(String app) {
        String appId = properties.appId(app);
        String flowDataId = app + dataIdSuffix();
        OpenNamespaceDTO openNamespaceDTO = apiClient.getNamespace(appId, properties.getEnv(), properties.getCluster(), properties.getNamespace());
        String rules = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().equals(flowDataId))
                .map(OpenItemDTO::getValue)
                .findFirst()
                .orElse("");

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

        // Increase the configuration
        String appId = properties.appId(app);
        String flowDataId = app + dataIdSuffix();
        OpenItemDTO item = new OpenItemDTO();
        item.setKey(flowDataId);
        item.setValue(JSON.toJSONString(rules));
        item.setComment(EDITOR + "auto-join");
        item.setDataChangeCreatedBy(EDITOR);
        apiClient.createOrUpdateItem(appId, properties.getEnv(), properties.getCluster(), properties.getNamespace(), item);

        // Release configuration
        NamespaceReleaseDTO namespace = new NamespaceReleaseDTO();
        namespace.setEmergencyPublish(true);
        namespace.setReleaseComment(EDITOR + "Modify or add configurations");
        namespace.setReleasedBy(EDITOR);
        namespace.setReleaseTitle(EDITOR + "Modify or add configurations");
        apiClient.publishNamespace(appId, properties.getEnv(), properties.getCluster(), properties.getNamespace(), namespace);
    }

    protected abstract String dataIdSuffix();
}
