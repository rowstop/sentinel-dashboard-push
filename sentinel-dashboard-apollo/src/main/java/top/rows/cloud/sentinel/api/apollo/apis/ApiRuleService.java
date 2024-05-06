package top.rows.cloud.sentinel.api.apollo.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.stereotype.Component;
import top.rows.cloud.sentinel.api.apollo.ApolloProperties;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ApiRuleService extends AbstractApolloRuleService<ApiDefinitionEntity> {


    public ApiRuleService(ApolloOpenApiClient apiClient, ApolloProperties apolloProperties) {
        super(apiClient, apolloProperties);
    }

    @Override
    protected String dataIdSuffix() {
        return properties.getDataIdSuffix().getApi();
    }
}
