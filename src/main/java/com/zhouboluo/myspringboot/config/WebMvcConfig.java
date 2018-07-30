package com.zhouboluo.myspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //允许所有跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(securityInterceptor).addPathPatterns("/**").excludePathPatterns("/", "/error", "/wlogin","/app","/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/**/*.html", "/**/*.js", "/**/*.css", "/**/*.png", "/**/*.svg", "/**/*.eot", "/**/*.woff", "/**/*.ttf", "/**/*.ico");//配置登录拦截器拦截路径
    }
}
