package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientDegradeRuleApi extends AbstractClientRuleApi<DegradeRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientDegradeRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }

    @Override
    protected List<DegradeRuleEntity> fetch(String app, String ip, int port) {
        return sentinelApiClient.fetchDegradeRuleOfMachine(app, ip, port);
    }

    @Override
    public boolean publish(String app, String ip, int port, List<DegradeRuleEntity> rules) {
        return sentinelApiClient.setDegradeRuleOfMachine(app, ip, port, rules);
    }
}
