package top.rows.cloud.sentinel.api.client.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleApi;
import com.alibaba.csp.sentinel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 张治保
 * @since 2024/4/25
 */
public abstract class AbstractClientRuleApi<T extends RuleEntity> implements DynamicRuleApi<T> {

    private final AppManagement appManagement;

    protected AbstractClientRuleApi(AppManagement appManagement) {
        this.appManagement = appManagement;
    }

    protected abstract List<T> fetch(String app, String ip, int port);

    protected abstract boolean publish(String app, String ip, int port, List<T> rules);


    @Override
    public List<T> getRules(String appName) throws Exception {
        if (StringUtil.isBlank(appName)) {
            return new ArrayList<>();
        }
        List<MachineInfo> list = appManagement.getDetailApp(appName).getMachines()
                .stream()
                .filter(MachineInfo::isHealthy)
                .sorted((e1, e2) -> Long.compare(e2.getLastHeartbeat(), e1.getLastHeartbeat())).collect(Collectors.toList());
        if (list.isEmpty()) {
            return new ArrayList<>();
        } else {
            MachineInfo machine = list.get(0);
            return fetch(machine.getApp(), machine.getIp(), machine.getPort());
        }
    }

    @Override
    public void publish(String app, List<T> rules) throws Exception {
        if (StringUtil.isBlank(app)) {
            return;
        }
        if (rules == null) {
            return;
        }
        Set<MachineInfo> set = appManagement.getDetailApp(app).getMachines();

        for (MachineInfo machine : set) {
            if (!machine.isHealthy()) {
                continue;
            }
            // TODO: parse the results
            publish(app, machine.getIp(), machine.getPort(), rules);
        }
    }
}
