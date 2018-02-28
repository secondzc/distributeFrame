package com.tongyuan.distributeFrame.demo.shiro.filter;

import com.tongyuan.distributeFrame.cache.shiro.RedisCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zhangcy on 2018/2/28
 *
 * 1.得到username和sessionId
 * 2.在缓存中由username得到队列，将sessionId放入队列中，查看队列是否满了，若满了，根据规则踢人
 * 3.对踢出的session进行操作
 */
public class OnlineLimitFilter extends AccessControlFilter {
    private final Logger logger = LogManager.getLogger();

    //是否踢出后来者
    private boolean kickoutAfter = true;
    //最大同时在线数(默认为1)
    private int maxSession = 1;
    //双端队列可以选择踢前面或后面
    private Cache<String,Deque<Serializable>> onlineLimitCache;
    private final static String namespace = "online_limit_cache:";

    public OnlineLimitFilter(CacheManager cacheManager) {
        this.onlineLimitCache = cacheManager.getCache(namespace);
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("isAccessAllowed executed");
        return false;
    }

    //若被filter处理则返回true，若需要被onAccessDenied处理则返回false
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        //放入队列中并更新缓存
        Deque<Serializable> onlineSessionIds = onlineLimitCache.get(username);
        if(onlineSessionIds == null){//说明此username还没有登录过
            onlineSessionIds = new LinkedList<Serializable>();
        }
        if(!onlineSessionIds.contains(sessionId)){
            onlineSessionIds.addLast(sessionId);
            onlineLimitCache.put(username,onlineSessionIds);
        }

        //开始踢人
        if(onlineSessionIds.size()>maxSession){
            Serializable kickoutSessonId = null;
            if(kickoutAfter){//踢后面的
                kickoutSessonId = onlineSessionIds.pollLast();
            }else{
                kickoutSessonId = onlineSessionIds.pollFirst();
            }
            onlineLimitCache.put(username,onlineSessionIds);
            //判断被踢出，若是，则return false，否则返回true
            if(kickoutSessonId.equals(sessionId)){
                subject.logout();
                logger.info("用户{}重复登录，被踢出了",username);
                //此处可以返回值给客户端作为提示
                return false;
            }
        }
        return true;
    }


}
