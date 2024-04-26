package top.rows.cloud.sentinel.api.nacos.apis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.stereotype.Component;
import top.rows.cloud.sentinel.api.nacos.NacosProperties;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Component
public class NacosDegradeRuleService extends AbstractNacosRuleService<DegradeRuleEntity> {

    protected NacosDegradeRuleService(ConfigService configService, NacosProperties nacosProperties) {
        super(configService, nacosProperties);
    }

    @Override
    protected String dataIdSuffix() {
        return nacosProperties.getDataIdSuffix().getDegrade();
    }
}
