package com.zhouboluo.myspringboot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WeixinSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private ExitSuccessHandler exitSuccessHandler;

    @Autowired
    private WeixinAuthenticationConfig weixinAuthenticationConfig;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()//关闭csrf token验证
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .apply(weixinAuthenticationConfig)//使用此套配置执行认证
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)//未鉴权时
                .accessDeniedHandler(authenticationAccessDeniedHandler)//已鉴权但权限不足时
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60 * 24 * 1)//session保存时长
                .and()
                .logout()
                .logoutSuccessHandler(exitSuccessHandler);//登出成功的处理
    }
}
