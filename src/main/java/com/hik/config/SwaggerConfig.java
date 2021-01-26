package com.hik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yzm on 2017/8/30.
 * Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
 * 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
 * Swagger 让部署管理和使用功能强大的API从未如此简单。/swagger-ui.html#
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket apiall() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("all")
//                .forCodeGeneration(true)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("/.*")) //过滤生成链接
//                .build()
//                .apiInfo(apiInfo());
//    }

    @Bean
    public Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfo("支付系统数据接口文档",
                "文档描述。。。",
                "1.0.0",
                "API TERMS URL",
                "786004470@qq.com",
                "license",
                "license url");
        return apiInfo;
    }

    //api接口作者相关信息
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("全部API").description("全部接口").version("1.0").build();
        return apiInfo;
    }

}
