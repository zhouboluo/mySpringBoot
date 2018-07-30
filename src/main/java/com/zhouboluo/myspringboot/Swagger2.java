package com.zhouboluo.myspringboot;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * swagger的配置文件
 *
 * @author lg
 * 2018-04-23 17:00
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        String address = "127.0.0.1";
        try {
            address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new ApiInfoBuilder()
                .title("我的项目 接口")
                .description("此页面展示了我的项目所有接口, 以及相关请求参数, 可以进行测试")
                .termsOfServiceUrl("http://" + address + ":9000")
                .contact(new Contact("zbl","www.zhouboluo.com","442699990@qq.com"))
                .version("1.0")
                .build();
    }
}
