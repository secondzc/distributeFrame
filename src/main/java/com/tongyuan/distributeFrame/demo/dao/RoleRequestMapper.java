package com.tongyuan.distributeFrame.demo.dao;

import com.tongyuan.distributeFrame.demo.entity.RoleRequest;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangcy on 2018/3/17
 */
public interface RoleRequestMapper {
    void setAgree(@Param("requsetId") Long requestId, @Param("roleId") Long roleId);
    void setDisagree(@Param("requsetId") Long requestId, @Param("roleId") Long roleId);
    void setActive(@Param("requsetId") Long requestId, @Param("roleId") Long roleId);
}
