package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientFlowRuleApi extends AbstractClientRuleApi<FlowRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientFlowRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<FlowRuleEntity> fetch(String app, String ip, int port) {
        return sentinelApiClient.fetchFlowRuleOfMachine(app, ip, port);
    }

    @Override
    public boolean publish(String app, String ip, int port, List<FlowRuleEntity> rules) {
        return sentinelApiClient.setFlowRuleOfMachine(app, ip, port, rules);
    }
}
