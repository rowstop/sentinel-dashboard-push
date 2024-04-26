package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientSystemRuleApi extends AbstractClientRuleApi<SystemRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientSystemRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<SystemRuleEntity> fetch(String app, String ip, int port) {
        return sentinelApiClient.fetchSystemRuleOfMachine(app, ip, port);
    }

    @Override
    public boolean publish(String app, String ip, int port, List<SystemRuleEntity> rules) {
        return sentinelApiClient.setSystemRuleOfMachine(app, ip, port, rules);
    }
}
