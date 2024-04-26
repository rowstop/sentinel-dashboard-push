package top.rows.cloud.sentinel.api.nacos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nacos")
public class NacosProperties {

    private final String serverAddr = "localhost:8848";

    private final String namespace = "public";

    private final String groupId = "SENTINEL";

    private String username;

    private String password;

    @NestedConfigurationProperty
    private final DataIdSuffix dataIdSuffix = new DataIdSuffix();

    @Getter
    @Setter
    public static class DataIdSuffix {
        private final String flow = "-flow.json";
        private final String degrade = "-degrade.json";
        private final String system = "-system.json";
        private final String api = "-api.json";
        private final String authority = "-authority.json";
        private final String paramFlow = "-param-flow.json";
        private final String gatewayFlow = "-gw-flow.json";
    }
}
