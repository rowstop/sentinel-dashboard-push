## 基于Sentinel 1.8.7，支持双向持久化到配置中心。支持 Gateway 模式与常规模式

## 实现方式 （以flow rule 和 nacos 配置中心举例）
![yuque_diagram (1)](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/a3a9ecb5-ed1a-45e1-8a75-d875d9ecdf90)

## 支持多种方式的推送 [官方 文档](https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel)

### 原始模式
![image](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/5ca45487-b121-4194-a655-f104790d3181)

### PUSH 模式
![image](https://github.com/rowstop/rowstop-sentinel-dashboard-push/assets/100893704/0051dafb-fc89-47f7-9b02-f733aa3d99f6)


### sentinel-dashboard-client

原始模式支持，官方原生支持原始模式，如果使用原始模式，不建议使用本项目，直接使用官方版本即可。

### sentinel-dashboard-nacos

push 模式， 实现了从 nacos拉取规则配置，支持新增、修改规则配置，由nacos下发规则配置到所有sentinel 客户端
