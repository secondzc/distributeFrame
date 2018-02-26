package com.tongyuan.distributeFrame.demo.service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhangcy on 2018/2/26
 */
public interface UserExtendService {
    Set<String> getRoles(String username);
    Set<String> getPermissions(String username);
}
