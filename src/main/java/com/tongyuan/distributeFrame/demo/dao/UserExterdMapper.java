package com.tongyuan.distributeFrame.demo.dao;

import java.util.List;

/**
 * Created by zhangcy on 2018/2/26
 */
public interface UserExterdMapper {
    List<String> getRoles(String username);
    List<String> getPermissions(String username);
}
