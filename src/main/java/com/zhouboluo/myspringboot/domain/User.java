package com.zhouboluo.myspringboot.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * @author zbl
 * 2018-07-19 11:07
 */
@Entity
@Data
@Table(name = "user")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false, length = 100)
    private String userName;

    /**
     * 密码（加密后）
     */
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    /**
     * 电话
     */
    @Column(name = "telephone", nullable = true, length = 64)
    private String telephone;

    /**
     * 用户类型
     */
    @Column(name = "type", nullable = true, length = 10)
    private Integer type;

    /**
     * 注册时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "wx_open_id", nullable = false)
    private String wxOpenId;

    /**
     * 是否已删除
     */
    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
