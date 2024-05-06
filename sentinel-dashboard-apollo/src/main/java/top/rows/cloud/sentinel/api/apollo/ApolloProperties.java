package top.rows.cloud.sentinel.api.apollo;

import com.alibaba.csp.sentinel.util.StringUtil;
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
@ConfigurationProperties(prefix = "apollo")
public class ApolloProperties {

    /**
     * apollo 服务端地址
     */
    private String portalUrl = "http://localhost:10034";

    /**
     * apollo token
     */
    private String token = "token";

    /**
     * apollo appId 如果为空则直接使用 动态的appName
     */
    private String appId;

    /**
     * 链接超时时间
     */
    private int connectTimeout = -1;

    /**
     * 读取超时时间
     */
    private int readTimeout = -1;

    private String env = "DEV";

    private String cluster = "default";

    private String namespace = "application";


    @NestedConfigurationProperty
    private DataIdSuffix dataIdSuffix = new DataIdSuffix();

    /**
     * apollo appId 如果为空则直接使用 动态的appName
     */
    public String appId(String appName) {
        return StringUtil.isEmpty(appId) ? appName : appId;
    }

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
