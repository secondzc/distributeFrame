package com.tongyuan.distributeFrame.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by zhangcy on 2018/2/26
 */
@TableName("user_role")
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
