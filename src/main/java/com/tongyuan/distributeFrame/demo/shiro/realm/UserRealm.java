package com.tongyuan.distributeFrame.demo.shiro.realm;

import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.demo.service.UserExtendService;
import com.tongyuan.distributeFrame.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by zhangcy on 2018/2/26
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserExtendService userExtendService;

    /*
    返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = userExtendService.getRoles(username);
        info.setRoles(roles);
        Set<String> permissions = userExtendService.getPermissions(username);
        info.setStringPermissions(permissions);
        return info;
    }

    /*
    返回认证角色信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.queryByUsername(username);
        if(user == null){
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"xx");
        return info;
    }
}
