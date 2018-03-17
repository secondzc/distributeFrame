package com.tongyuan.distributeFrame.demo.check.enums;

/**
 * Created by zhangcy on 2018/3/17
 */
public enum StrategyEnum {
    LinkedStrategy("linkedStrategy"),
    HyFirstStrategy("hyFirstStrategy"),
    MaLastStrategy("maLastStrategy")
    ;

    private String strategy;

    StrategyEnum(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
