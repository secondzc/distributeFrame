package com.tongyuan.distributeFrame.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by zhangcy on 2018/3/17
 */
@TableName("request")
public class Request {
    private Long id;
    private String strategy;
    private String status;
    private String currentRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }
}
