package com.zhouboluo.myspringboot.config.security;

import com.zhouboluo.myspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Weixin auth polymeric config.
 *
 * @author haffxu
 */
@Configuration
public class WeixinAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserService userService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        //自定义UsernameAuthenticationFilter
        WeixinAuthenticationFilter filter = new WeixinAuthenticationFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        //设置自定义CustomKeyAuthenticationProvider的认证器userDetailsService
        WeixinAuthenticationProvider provider = new WeixinAuthenticationProvider(userService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        builder.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
