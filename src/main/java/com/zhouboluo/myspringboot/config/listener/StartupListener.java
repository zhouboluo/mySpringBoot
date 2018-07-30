package com.zhouboluo.myspringboot.config.listener;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Spring boot application listener.
 *
 * @author haffxu
 */
@Configuration
@Order(1)
public class StartupListener implements ApplicationRunner {
    @Value("${weixin.app-id}")
    private String appId;

    @Value("${weixin.app-secret}")
    private String appSecret;

    @Override
    public void run(ApplicationArguments args) {
        ApiConfig ac = new ApiConfig();
        ac.setAppId(appId);
        ac.setAppSecret(appSecret);
        ApiConfigKit.putApiConfig(ac);
    }
}
