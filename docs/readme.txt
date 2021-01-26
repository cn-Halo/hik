pay支付
===

## 说明

- 项目基于 `spring-boot` 构建
- 实现支付宝支付功能
- 项目目录按 `spring boot` 约定
```
main
    java
        com.pay.xx
    resources
        resources(注意，所有web资源存放到该目录)
        templates(freemarker file)
```
    
## 运行方式

- 打包

    mvn install 

- 运行
    
    java -jar .\pay-0.0.1-SNAPSHOT.jar  -Dspring.profiles.active=prod
    

2017.12.15
---

## 开票登记

- 通过支付平台付款的客户，公司会开具发票，系统需登记该信息，注意：不是直接登记任意客户信息，而是通过支付平台付款的客户
- 能区分哪些应用调用了该支付平台
- 提供5张查询报表
