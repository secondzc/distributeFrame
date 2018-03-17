package com.tongyuan.distributeFrame.demo.dao;

import com.tongyuan.distributeFrame.demo.entity.Request;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangcy on 2018/3/17
 */
public interface RequestMapper {
    Request queryById(Long id);
    void setStatus(@Param("status")String status,@Param("id") Long id);
    void setCurrentRole(@Param("current")String current,@Param("id")Long id);
}
