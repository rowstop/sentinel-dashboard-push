## 基于Sentinel 1.8.7，支持双向持久化到配置中心。支持 Gateway 模式与常规模式
[官方 文档](https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel)

## 实现方式 （以flow rule 和 nacos 配置中心举例）
![yuque_diagram (1)](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/a3a9ecb5-ed1a-45e1-8a75-d875d9ecdf90)

### 原始模式
![image](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/5ca45487-b121-4194-a655-f104790d3181)

### PUSH 模式
![image](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/0051dafb-fc89-47f7-9b02-f733aa3d99f6)

### sentinel-dashboard-client

原始模式支持，官方原生支持原始模式，如果使用原始模式，不建议使用本项目，直接使用官方版本即可。

### sentinel-dashboard-nacos

push 模式， 实现了从 nacos拉取规则配置，支持新增、修改规则配置，由nacos下发规则配置到所有sentinel 客户端
#### 启动 `java -Dnacos.server-addr=127.0.0.1:8848 -Dnacos.namespace=public  -jar sentinel-dashboard-nacos-1.8.7.jar`
#### 访问 127.0.0.1:8080
### 配置
```java
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
```
