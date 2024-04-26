package top.rows.cloud.sentinel.api.nacos;

import com.alibaba.csp.sentinel.dashboard.DashboardApplication;

/**
 * 仅在 开发环境调试使用
 *
 * @author 张治保
 * @since 2024/4/26
 */
public class DevMain {

    public static void main(String[] args) {
        String[] arguments = new String[]{
                "--server.port=8080",
                //nacos 服务端地址
                "--nacos.server-addr=127.0.0.1:8848",
                //sentinel 配置所在的命名空间
                "--nacos.namespace=springboot3",
                //sentinel 配置所在的组
                "--nacos.group-id=SENTINEL",
        };
        DashboardApplication.main(arguments);
    }

}
