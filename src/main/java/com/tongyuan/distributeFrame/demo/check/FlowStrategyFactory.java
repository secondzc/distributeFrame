package com.tongyuan.distributeFrame.demo.check;

import com.tongyuan.distributeFrame.demo.check.enums.StrategyEnum;
import com.tongyuan.distributeFrame.demo.check.strategyImpl.HyFirstStrategy;
import com.tongyuan.distributeFrame.demo.check.strategyImpl.LinkedStrategy;
import com.tongyuan.distributeFrame.demo.check.strategyImpl.MaLastStrategy;
import com.tongyuan.distributeFrame.util.InstanceUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangcy on 2018/3/17
 */
public class FlowStrategyFactory implements InitializingBean{
    @Autowired
    private LinkedStrategy linkedStrategy;
    @Autowired
    private HyFirstStrategy hyFirstStrategy;
    @Autowired
    private MaLastStrategy maLastStrategy;

    private static final Map<String,FlowStrategy> flowStrategyMap = InstanceUtil.newHashMap();

    public FlowStrategy create(String strategy){
        //默认按照linkedStrategy流程
        FlowStrategy flowStrategy = flowStrategyMap.get(strategy);
        if(null == flowStrategy){
            return linkedStrategy;
        }
        return flowStrategy;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       flowStrategyMap.put(StrategyEnum.LinkedStrategy.getStrategy(),linkedStrategy);
       flowStrategyMap.put(StrategyEnum.HyFirstStrategy.getStrategy(),hyFirstStrategy);
       flowStrategyMap.put(StrategyEnum.MaLastStrategy.getStrategy(),maLastStrategy);
    }
}
