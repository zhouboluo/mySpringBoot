package com.zhouboluo.myspringboot.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WeixinAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    //构建未经认证的token
    public WeixinAuthenticationToken(String principal) {
        super(null);
        this.principal = principal;
        super.setAuthenticated(false);//必须用父类的方法
    }

    //构建已经过认证的token
    public WeixinAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);//必须用父类的方法
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
