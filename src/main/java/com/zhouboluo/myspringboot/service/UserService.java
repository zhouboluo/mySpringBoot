package com.zhouboluo.myspringboot.service;

import com.jfinal.weixin.sdk.api.*;
import com.zhouboluo.myspringboot.domain.User;
import com.zhouboluo.myspringboot.domain.repository.UserRepository;
import com.zhouboluo.myspringboot.vo.BaseResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 用户Service
 * @author zbl
 * 2018-07-19 11:07
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    /**
     * login by weixin code
     *
     * @param code weixin code
     * @return login ret
     */
    public BaseResultVO login(String code) {
        BaseResultVO baseResultVO = new BaseResultVO();
        if (StringUtils.isEmpty(code)) {
            baseResultVO.setMessage("微信登录code不能为空");
            return baseResultVO;
        }
        // 获取SessionKey
        SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(ApiConfigKit.getApiConfig().getAppId(), ApiConfigKit.getApiConfig().getAppSecret(), code);
        if (!snsAccessToken.isAvailable()) {
            baseResultVO.setMessage(snsAccessToken.getErrorMsg());
            return baseResultVO;
        }

        User user = userRepository.findTopByWxOpenId(snsAccessToken.getOpenid());
        if (user == null) {
            user = saveWeixinUser(snsAccessToken);
        }
        baseResultVO.setSuccess(true);
        baseResultVO.setValues("user",user);
        return baseResultVO;
    }

    /**
     * save new weixin user
     *
     * @param snsAccessToken token
     * @return user
     */
    private User saveWeixinUser(SnsAccessToken snsAccessToken) {
        ApiResult userinfo = SnsApi.getUserInfo(snsAccessToken.getAccessToken(), snsAccessToken.getOpenid());

        User user = new User();
        user.setWxOpenId(snsAccessToken.getOpenid());
        //user.setRefreshToken(snsAccessToken.getRefresh_token());
        if (userinfo.isSucceed()) {
            user.setCreateTime(new Date());
            /*user.setNickName(userinfo.getStr("nickname"));
            user.setPhoto(userinfo.getStr("headimgurl"));
            user.setGender(userinfo.getInt("sex"));
            user.setProvince(userinfo.getStr("province"));
            user.setCity(userinfo.getStr("city"));
            user.setCountry(userinfo.getStr("country"));*/
        }
        userRepository.save(user);
        return user;
    }

}
