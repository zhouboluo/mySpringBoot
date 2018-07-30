package com.zhouboluo.myspringboot.config.security;

import com.zhouboluo.myspringboot.service.UserService;
import com.zhouboluo.myspringboot.vo.BaseResultVO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class WeixinAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    public WeixinAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String weixinCode = String.valueOf(authentication.getPrincipal());
        if (weixinCode.isEmpty()) {
            throw new UsernameNotFoundException("微信返回code不能为空");
        }
        BaseResultVO baseResultVO = this.userService.login(weixinCode);
        if (!baseResultVO.isSuccess()) {
            throw new UsernameNotFoundException(baseResultVO.getMessage());
        }
        WeixinAuthenticationToken authenticationToken = new WeixinAuthenticationToken(baseResultVO.getValues("user"), null);
        authenticationToken.setDetails(authentication.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
