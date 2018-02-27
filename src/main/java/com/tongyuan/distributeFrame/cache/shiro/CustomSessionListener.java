package com.tongyuan.distributeFrame.cache.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by zhangcy on 2018/2/27
 */
public class CustomSessionListener implements SessionListener {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void onStart(Session session) {
        logger.info("on start");
    }

    @Override
    public void onStop(Session session) {
        logger.info("on stop");
    }

    @Override
    public void onExpiration(Session session) {

    }
}
