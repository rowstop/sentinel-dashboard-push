package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class ClientParamFlowRuleApi extends AbstractClientRuleApi<ParamFlowRuleEntity> {

    private final SentinelApiClient sentinelApiClient;

    protected ClientParamFlowRuleApi(AppManagement appManagement, SentinelApiClient sentinelApiClient) {
        super(appManagement);
        this.sentinelApiClient = sentinelApiClient;
    }


    @Override
    protected List<ParamFlowRuleEntity> fetch(String app, String ip, int port) {
        try {
            return sentinelApiClient.fetchParamFlowRulesOfMachine(app, ip, port).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean publish(String app, String ip, int port, List<ParamFlowRuleEntity> rules) {
        try {
            sentinelApiClient.setParamFlowRuleOfMachine(app, ip, port, rules)
                    .get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }
}
