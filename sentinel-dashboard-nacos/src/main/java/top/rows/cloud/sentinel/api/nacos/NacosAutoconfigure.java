package top.rows.cloud.sentinel.api.nacos;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Configuration
@ComponentScan(basePackages = "top.rows.cloud.sentinel.api.nacos.apis")
@EnableConfigurationProperties(NacosProperties.class)
public class NacosAutoconfigure {

    @Bean
    public ConfigService nacosConfigService(NacosProperties nacosProperties) throws Exception {
        String serverAddr = nacosProperties.getServerAddr();
        String namespace = nacosProperties.getNamespace();

        Properties properties = new Properties();
        //设置nacos服务地址
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        //设置nacos命名空间
        properties.put(PropertyKeyConst.NAMESPACE, namespace);
        if (StringUtil.isNotBlank(nacosProperties.getUsername())) {
            properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
            properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        }
        return ConfigFactory.createConfigService(properties);
    }
}
