package com.zhouboluo.myspringboot.config.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Weixin login filter.
 *
 * @author haffxu
 */
public class WeixinAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected WeixinAuthenticationFilter() {
        super(new AntPathRequestMatcher("/auth/weixin", "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String principal = request.getParameter("code");
        if (StringUtils.isEmpty(principal)) {
            throw new BadCredentialsException("微信用户登录code无法获取");
        }
        WeixinAuthenticationToken token = new WeixinAuthenticationToken(principal);
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }
}