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

    /**
     * nacos 服务端地址
     */
    private String serverAddr = "localhost:8848";
    /**
     * nacos 服务端用户名 服务端未开启用户名密码验证可以留空或者不配置
     */
    private String username;
    /**
     * nacos 服务端密码 服务端未开启用户名密码验证可以留空或者不配置
     */
    private String password;
    /**
     * sentinel 配置所在的命名空间
     */
    private String namespace = "public";
    /**
     * sentinel 配置所在的组
     */
    private String groupId = "SENTINEL";


    @NestedConfigurationProperty
    private DataIdSuffix dataIdSuffix = new DataIdSuffix();

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
