package top.rows.cloud.sentinel.api.apollo.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.stereotype.Component;
import top.rows.cloud.sentinel.api.apollo.ApolloProperties;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class FlowRuleService extends AbstractApolloRuleService<FlowRuleEntity> {


    public FlowRuleService(ApolloOpenApiClient apiClient, ApolloProperties properties) {
        super(apiClient, properties);
    }

    @Override
    protected String dataIdSuffix() {
        return properties.getDataIdSuffix().getFlow();
    }
}
