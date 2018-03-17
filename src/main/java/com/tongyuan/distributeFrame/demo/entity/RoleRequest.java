package com.tongyuan.distributeFrame.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by zhangcy on 2018/3/17
 */
@TableName("role_request")
public class RoleRequest {
    private Long id;
    private Long roleId;
    private Long requestId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
