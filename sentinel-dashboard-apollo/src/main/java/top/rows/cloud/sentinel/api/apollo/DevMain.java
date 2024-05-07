package top.rows.cloud.sentinel.api.apollo;

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
                "--apollo.portal-url=http://localhost:10034",
                "--apollo.token=token",
                "--apollo.env=DEV"
        };
        DashboardApplication.main(arguments);
    }

}
