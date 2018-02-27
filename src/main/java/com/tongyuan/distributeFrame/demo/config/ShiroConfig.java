package com.tongyuan.distributeFrame.demo.config;

import com.tongyuan.distributeFrame.demo.shiro.listener.CustomSessionListener;
import com.tongyuan.distributeFrame.cache.shiro.RedisCacheManager;
import com.tongyuan.distributeFrame.demo.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcy on 2018/2/27
 */
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(UserRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(new RedisCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
//        //注入记住我管理器;
//        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public UserRealm UserRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        List<SessionListener> sessionListeners = new ArrayList<SessionListener>();
        sessionListeners.add(new CustomSessionListener());
        sessionManager.setSessionListeners(sessionListeners);
        //相隔多久检查一次session的有效性
        sessionManager.setSessionValidationInterval(1800000);
        //session有效时间为半个小时（毫秒为单位）
        sessionManager.setGlobalSessionTimeout(1800000);
        return sessionManager;
    }
}
