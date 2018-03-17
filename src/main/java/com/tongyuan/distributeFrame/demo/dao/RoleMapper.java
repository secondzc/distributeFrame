package com.tongyuan.distributeFrame.demo.dao;

import com.tongyuan.distributeFrame.demo.entity.Role;

/**
 * Created by zhangcy on 2018/3/17
 */
public interface RoleMapper {
    Role selectByRoleName(String roleName);
}
