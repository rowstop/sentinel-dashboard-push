## 实现方式
![yuque_diagram (1)的副本](https://github.com/rowstop/sentinel-dashboard-plus/assets/100893704/995bf4b0-81e7-41b6-9b25-048eee56f359)


## 基于Sentinel 1.8.7，支持双向持久化到配置中心。支持 Gateway 模式与常规模式

## 支持多种方式的推送方式 官方推荐在生产中使用PUSH模式

[官方 文档](https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel)

### 原始模式

![image](https://github.com/rowstop/sentinel-dashboard-plus/assets/100893704/3f8b1d14-9f99-46e6-84ee-6e85e5454818)

### PUSH 模式

![image](https://github.com/rowstop/sentinel-dashboard-plus/assets/100893704/d39fe69e-35be-4128-b5bc-48cd1ac27c26)

### sentinel-dashboard-client

原始模式支持，官方原生支持原始模式，如果使用原始模式，不建议使用本项目，直接使用官方版本即可。

### sentinel-dashboard-nacos

push 模式， 实现了从 nacos拉取规则配置，支持新增、修改规则配置，由nacos下发规则配置到所有sentinel 客户端
