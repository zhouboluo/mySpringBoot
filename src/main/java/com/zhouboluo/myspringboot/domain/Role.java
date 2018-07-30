package com.zhouboluo.myspringboot.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色表
 * @author zbl
 * 2018-07-19 11:07
 */
@Entity
@Data
@Table(name = "role")
public class Role {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

}
