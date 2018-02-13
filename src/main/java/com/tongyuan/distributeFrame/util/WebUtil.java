package com.tongyuan.distributeFrame.util;

import com.tongyuan.distributeFrame.constant.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by zhangcy on 2018/2/12
 */
public final class WebUtil {

    private WebUtil(){

    }

    private static Logger logger = LogManager.getLogger(WebUtil.class);
    /** 获取当前用户 */
    public static final Long getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            try {
                Session session = currentUser.getSession();
                if (null != session) {
                    return (Long) session.getAttribute(Constants.CURRENT_USER);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
        }
        return null;
    }
}
