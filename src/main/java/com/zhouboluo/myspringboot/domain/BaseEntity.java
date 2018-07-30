package com.zhouboluo.myspringboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础实体
 *
 * @author zbl
 * 2018-07-27
 */
@MappedSuperclass
@Data
public class BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @ManyToOne
    @JoinColumn(name = "create_user_id")
    private User createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @ManyToOne
    @JoinColumn(name = "update_user_id")
    private User updateUser;

    /**
     * 是否已删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete = false;
}
