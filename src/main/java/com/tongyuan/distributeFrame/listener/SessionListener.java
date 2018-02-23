package com.tongyuan.distributeFrame.listener;

import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.util.CacheUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by zhangcy on 2018/2/23
 */
public class SessionListener implements HttpSessionListener {
    private Logger logger = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("创建session连接：["+session.getId()+"]");
        CacheUtil.getCacheManager().sadd(Constants.ALL_USER_NUMBER,session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("销毁session连接：[" + session.getId() +"]");
        CacheUtil.getCacheManager().sdel(Constants.ALL_USER_NUMBER,session.getId());
    }

    /*
    获取在线用户数量
     */
    public Integer getUserNumber(){
        return CacheUtil.getCacheManager().sall(Constants.ALL_USER_NUMBER).size();
    }
}
