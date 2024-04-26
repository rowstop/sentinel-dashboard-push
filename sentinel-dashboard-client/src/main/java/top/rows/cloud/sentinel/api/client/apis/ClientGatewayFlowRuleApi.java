package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientGatewayFlowRuleApi extends AbstractClientRuleApi<GatewayFlowRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientGatewayFlowRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<GatewayFlowRuleEntity> fetch(String app, String ip, int port) {
        try {
            return sentinelApiClient.fetchGatewayFlowRules(app, ip, port).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean publish(String app, String ip, int port, List<GatewayFlowRuleEntity> rules) {
        return sentinelApiClient.modifyGatewayFlowRules(app, ip, port, rules);
    }
}
