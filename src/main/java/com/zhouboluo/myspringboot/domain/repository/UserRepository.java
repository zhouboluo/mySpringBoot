package com.zhouboluo.myspringboot.domain.repository;

import com.zhouboluo.myspringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户Repository
 * @author zbl
 * 2018-07-19 11:07
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findTopByWxOpenId(String openId);
}
