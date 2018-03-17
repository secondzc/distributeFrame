package com.tongyuan.distributeFrame.demo.check.enums;

/**
 * Created by zhangcy on 2018/3/17
 */
public enum NodeStatus {
    NotActive("notActive"),
    Agree("agree"),
    Disagree("disagree"),
    handling("handling")
    ;

    private String status;

    NodeStatus(String status) {
        this.status = status;
    }
}
