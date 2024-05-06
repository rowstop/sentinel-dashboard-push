package top.rows.cloud.sentinel.api.apollo;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张治保
 * @since 2024/4/25
 */
@Configuration
@ComponentScan(basePackages = "top.rows.cloud.sentinel.api.apollo.apis")
@EnableConfigurationProperties(ApolloProperties.class)
public class ApolloAutoconfigure {

    @Bean
    public ApolloOpenApiClient apolloOpenApiClient(ApolloProperties properties) {
        return ApolloOpenApiClient.newBuilder()
                .withPortalUrl(properties.getPortalUrl())
                .withToken(properties.getToken())
                .withConnectTimeout(properties.getConnectTimeout())
                .withReadTimeout(properties.getReadTimeout())
                .build();
    }
}
