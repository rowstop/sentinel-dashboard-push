package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientApiRuleApi extends AbstractClientRuleApi<ApiDefinitionEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientApiRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<ApiDefinitionEntity> fetch(String app, String ip, int port) {
        try {
            return sentinelApiClient.fetchApis(app, ip, port).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean publish(String app, String ip, int port, List<ApiDefinitionEntity> rules) {
        return sentinelApiClient.modifyApis(app, ip, port, rules);
    }
}
