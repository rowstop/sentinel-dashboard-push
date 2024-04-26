package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientAuthorityFlowRuleApi extends AbstractClientRuleApi<AuthorityRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientAuthorityFlowRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<AuthorityRuleEntity> fetch(String app, String ip, int port) {
        return sentinelApiClient.fetchAuthorityRulesOfMachine(app, ip, port);
    }

    @Override
    public boolean publish(String app, String ip, int port, List<AuthorityRuleEntity> rules) {
        return sentinelApiClient.setAuthorityRuleOfMachine(app, ip, port, rules);
    }
}
