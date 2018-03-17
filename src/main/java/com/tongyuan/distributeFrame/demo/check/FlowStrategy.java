package com.tongyuan.distributeFrame.demo.check;


/**
 * Created by zhangcy on 2018/3/16
 */
public interface FlowStrategy {
    //开始流程
    void start(Long requestId);
    void agree(Long requestId);
    void disagree(Long requestId);
}
