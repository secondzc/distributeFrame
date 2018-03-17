package com.tongyuan.distributeFrame.demo.service;

import com.tongyuan.distributeFrame.demo.check.FlowStrategy;

/**
 * Created by zhangcy on 2018/3/17
 */
public interface FlowService {
    void startFlow(Integer requestId, FlowStrategy flowStrategy);
    void agree(Long requestId);
    void disagree(Long requestId);
}
