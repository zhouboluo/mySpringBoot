package com.zhouboluo.myspringboot.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 服务层结果集
 */

@Data
public class BaseResultVO implements Serializable {

    private static final long serialVersionUID = 3089356206859138988L;

    /**
     * 执行结果
     */
    private boolean success = false;

    /**
     * 执行结果code标示
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果集
     */
    private Map<String, Object> values = new HashMap<String, Object>();

    public Object getValues(String key) {
        return values.get(key);
    }

    public void setValues(String key,Object value) {
        this.values.put(key, value);
    }

}
